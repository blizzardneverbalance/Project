import pandas as pd
import numpy as np


def q1(df):
    """
    How many cars are made by the division Acura?
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: integer - the cars are made by the manufacturer Acura
    """
    return len(df[df["Division"] == 'Acura'])


def q2(df):
    """
    How many 'Guzzlers' are made by the manufacturer General Motors?
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: integer - 'Guzzlers' are made by the manufacturer General Motors
    """
    return len(df[(df['Guzzler?'] == 'G') &
                  (df["Mfr Name"] == 'General Motors')])


def q3(df):
    """
    What is the value of the lowest combined Fuel Efficiency as given by
    "Comb FE (Guide) - Conventional Fuel"?
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: float - The value of the lowest combined Fuel Efficiency as given
    by "Comb FE (Guide) - Conventional Fuel"
    """
    return np.min(df['Comb FE (Guide) - Conventional Fuel'])


def q4(df):
    """
    What car line has the highest Highway FE - Conventional Fuel as given by
    "Hwy FE (Guide) - Conventional Fuel"?
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: string - The car line has the highest Highway FE - Conventional
    Fuel as given by "Hwy FE (Guide) - Conventional Fuel"
    """
    return df.loc[df['Hwy FE (Guide) - Conventional Fuel'].idxmax(), 'Carline']


def q5(df):
    """
    What is the average combined FE - Conventional Fuel among all wheel drives.
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: float - The average combined FE - Conventional Fuel among all
    wheel drives.
    """
    df_all = df[df['Drive Desc'] == 'All Wheel Drive']
    return np.mean(df_all['Comb FE (Guide) - Conventional Fuel'])


def q6(df):
    """
    Which car line has the largest difference between Highway and City Fuel
    efficiency - Conventional Fuel?
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: string - The car line has the largest difference between Highway
    and City Fuel efficiency - Conventional Fuel.
    """
    return df.loc[(df['Hwy FE (Guide) - Conventional Fuel'] -
                        df['Comb FE (Guide) - Conventional Fuel']).idxmax(),
                  'Carline']


def q7(df):
    """
    What is the average annual fuel cost (Annual Fuel1 Cost - Conventional Fuel)
    of supercharged cars?
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: float - The average annual fuel cost (Annual Fuel1 Cost -
    Conventional Fuel) of supercharged carsl.
    """
    df_supercharged = df[df['Air Aspiration Method Desc'] == 'Supercharged']
    return np.mean(df_supercharged['Annual Fuel1 Cost - Conventional Fuel'])


def q8(df):
    """
    What SUV has the highest annual fuel cost?
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: string - SUV has the highest annual fuel cost.
    """
    df_suv = df[df['Carline Class Desc'].str.contains('SUV', na=False)]
    return df.loc[df_suv['Annual Fuel1 Cost - Conventional Fuel'].idxmax(),
                  'Carline']


def q9(df):
    """
    Which manufacturer has the most cars with manual transmission?
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: string - The manufacturer has the most cars with manual
    transmission.
    """
    return df[df['Transmission'].str.contains('Manual',
            na=False)].groupby('Mfr Name').count()['Transmission'].idxmax()


def q10(df):
    """
    What is the average annual fuel cost by car division?
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: Pandas series - The average annual fuel cost by car division.
    """
    return df.groupby('Division').mean()['Annual Fuel1 Cost - Conventional '
                                         'Fuel']


def q11(df):
    """
    I want to buy a Benz car with the lowest annual fuel cost.
    :param df: Pandas DataFrame representing the data in 2019 FE Guide.csv
    :return: string - The carline with the lowest annual fuel cost in Benz.
    """
    df_benz = df[df['Mfr Name'].str.contains('Benz', na=False)]
    return df.loc[df_benz['Annual Fuel1 Cost - Conventional Fuel'].idxmin(),
                  'Carline']


def main():
    df_fe = pd.read_csv('2019 FE Guide.csv')
    print(f'Q1: {q1(df_fe)}')
    print(f'Q2: {q2(df_fe)}')
    print(f'Q3: {q3(df_fe)}')
    print(f'Q4: {q4(df_fe)}')
    print(f'Q5: {q5(df_fe)}')
    print(f'Q6: {q6(df_fe)}')
    print(f'Q7: {q7(df_fe)}')
    print(f'Q8: {q8(df_fe)}')
    print(f'Q9: {q9(df_fe)}')
    print(f'Q10: {q10(df_fe)}')
    print(f'Q11: {q11(df_fe)}')


if __name__ == "__main__":
    main()