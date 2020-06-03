const prompt = require("prompt-sync")();
const axios = require("axios");
// process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";
const {Client, Status} = require("@googlemaps/google-maps-services-js");
// Requiring fs module in which 
// writeFile function is defined. 
const fs = require('fs') 
const client = new Client({});

var shippo = require("shippo")(
  "Shippo_API_Key"
);

const name = prompt("What is your name?");
console.log(`Hey there ${name}`);
const city = prompt("What is your city? ");
const state = prompt("What is your state? ");
const zip = prompt("What is your zip? ");
const street = prompt("What is your street address?");

shippo.address.create({
    name: name,
    street1: street,
    city: city,
    state: state,
    zip: zip,
    country: "US",
    email: "shippotle@goshippo.com",
    validate: true,
  })
  .then((response) => {
    // fs.writeFile('result.txt', response, (err) => { if (err) throw err; }) // response type err
    const isValid = response.validation_results.is_valid;
    console.log(isValid);
    return isValid;
  })
  .then((isValid) => {
    if (isValid) {
      //   const baseURL = "https://localhost:54492";
      const key =
        "Bing_API_Key";
      const baseURL = "http://dev.virtualearth.net";
      const googlekey =
        "Google_Map_API_Key";
      const googleURL = "https://maps.googleapis.com/maps/api/directions/json?";
      const origin = "Disneyland";
      const destination = "Universal+Studios+Hollywood";
      
      axios
        .get(
          `${baseURL}/REST/v1/Locations/US/${state}/${zip}/${city}/${street}/?key=${key}`
        )
        .then((res) => {
          console.log(res.data.resourceSets[0].resources[0].point.coordinates);
        })
        .catch((err) => console.log("Error ", err));

        axios.get(`${googleURL}origin=${origin}&destination=${destination}&key=${googlekey}`)
        .then((res) => {
            console.log("distance: " + res.data.routes[0].legs[0].distance.text);
          console.log("duration: " + res.data.routes[0].legs[0].duration.text);
          console.log("direction " + res.data.routes[0].summary);
        })
        .catch((err) => console.log("Error ", err));

    } else {
      throw new Error("address not valid");
    }
  })
  .catch((err) => console.log("Error ", err));

