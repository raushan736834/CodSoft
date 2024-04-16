import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyConverter {
    private static final String API_KEY = "2f564c4cf4cf7a05ee7e5f40";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/2f564c4cf4cf7a05ee7e5f40/latest/USD";

    public static void main(String[] args) {
        try {
            // Get exchange rates from the API
            JSONObject exchangeRates = getExchangeRates();

            if (exchangeRates == null) {
                System.out.println("Failed to retrieve exchange rates. Please try again later.");
                return;
            }

            // Display available currencies
            System.out.println("Available currencies:");
            JSONObject rates = exchangeRates.getJSONObject("conversion_rates");
            Iterator<String> keys = rates.keys();
            while (keys.hasNext()) {
                String currency = keys.next();
                System.out.println(currency);
            }

            // User input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter the base currency (e.g., USD):");
            String baseCurrency = reader.readLine().toUpperCase();
            System.out.println("Please enter the desired currency (e.g., EUR):");
            String desiredCurrency = reader.readLine().toUpperCase();
            System.out.println("Please enter the amount to convert:");
            double amount = Double.parseDouble(reader.readLine());

            // Convert
            double convertedAmount = convertCurrency(amount, baseCurrency, desiredCurrency, exchangeRates);
            System.out.printf("%.2f %s is equal to %.2f %s%n", amount, baseCurrency, convertedAmount, desiredCurrency);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getExchangeRates() throws IOException, JSONException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return new JSONObject(response.toString());
        } else {
            System.out.println("Failed to fetch exchange rates. Response code: " + responseCode);
            return null;
        }
    }

    private static double convertCurrency(double amount, String baseCurrency, String desiredCurrency, JSONObject exchangeRates) throws JSONException {
        double baseRate = exchangeRates.getJSONObject("conversion_rates").getDouble(baseCurrency);
        double desiredRate = exchangeRates.getJSONObject("conversion_rates").getDouble(desiredCurrency);
        return (amount / baseRate) * desiredRate;
    }
}
