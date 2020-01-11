from flask import Flask, request, render_template
from flask_sqlalchemy import SQLAlchemy
app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///restaurants.db'
db = SQLAlchemy(app)


class Restaurant(db.Model):
    """
    Class to represent and access the restaurant table.
    Attributes:
    id (integer)
    name (string)
    location (string)
    category (string)
    price (integer)
    """
    __tablename__ = "restaurant"
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String)
    location = db.Column(db.String)
    category = db.Column(db.String)
    price = db.Column(db.Integer)


@app.route('/')
@app.route('/home')
def welcome():
    results = Restaurant.query.all()
    results.reverse()
    for result in results:
        result.price = result.price * '$'
    if len(results) > 5:
        results = results[0:5]
    return render_template('home.html', results=results)


@app.route('/add', methods=["POST", "GET"])
def add():
    if request.method == "POST":
        name_input = request.form.get('name')
        category_input = request.form.get('category')
        location_input = request.form.get('location')
        price_input = request.form.get('price')
        new_restaurant = Restaurant(name=name_input, category=category_input,
                            location=location_input, price=price_input)
        db.session.add(new_restaurant)
        db.session.commit()
    return render_template('add.html')


@app.route('/view', methods=["POST", "GET"])
def view():
    results = []
    price = ''
    if request.method == "POST":
        price = request.form.get('price')
        category = request.form.get('category')
        location = request.form.get('location')
        query = Restaurant.query
        if price:
            query = query.filter(Restaurant.price <= price)
        if category:
            query = query.filter(Restaurant.category == category)
        if location:
            query = query.filter(Restaurant.location == location)
        results = query.all()
        for result in results:
            result.price = result.price * '$'
    return render_template('view.html', results=results, price=price)


def main():
    app.run(debug=True)


if __name__ == "__main__":
    main()