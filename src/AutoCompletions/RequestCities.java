package AutoCompletions;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.mansierra.utils.Calc;
import android.location.Location;
import android.util.Log;

public class RequestCities
{
	private static String PROTOCOL = "https:";
	private static String PLACES_BASE_API = "//api.goeuro.de/api/v1/suggest/position";
	private static String LANGUAGE = "/en";
	private static String METHOD = "/name";
	private static ArrayList<CityPositions> citysPositions;

	public static ArrayList<String> request(String inputTyped, Location CurrentLocation)
	{
		return SortByCurrentPosition(httpGet(inputTyped), CurrentLocation);
	}

	private static ArrayList<String> SortByCurrentPosition(String httpResult, Location CurrentLocation)
	{
		ArrayList<String> cities = null;
		try
		{
			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(httpResult);
			JSONArray jsonArray = jsonObj.getJSONArray("results");

			// Extract the Place descriptions from the results
			cities = new ArrayList<String>(jsonArray.length());
			citysPositions = new ArrayList<CityPositions>();
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonGeo = jsonArray.getJSONObject(i).getJSONObject("geo_position");
				citysPositions.add(new CityPositions(jsonArray.getJSONObject(i).getString("name"), Calc.distFrom((float) CurrentLocation.getLatitude(),
						(float) CurrentLocation.getLongitude(), (float) jsonGeo.getDouble("latitude"), (float) jsonGeo.getDouble("longitude"))));
			}
			Collections.sort(citysPositions, new CompareByGeoPosition());
			for (CityPositions city : citysPositions)
				cities.add(city.name);

		} catch (JSONException e)
		{
			Log.e("RequestCities", "Unable process JSON results", e);
			e.printStackTrace();
		}

		return cities;
	}

	private static String httpGet(String inputTyped)
	{
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try
		{
			StringBuilder sb = new StringBuilder(PROTOCOL + PLACES_BASE_API + LANGUAGE + METHOD);
			sb.append("/" + inputTyped);
			java.lang.System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
			X509TrustManager trustManager = new X509TrustManager()
			{
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
				{
					// Don't do anything.
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
				{
					// Don't do anything.
				}

				public X509Certificate[] getAcceptedIssuers()
				{
					return new java.security.cert.X509Certificate[0];
				}
			};

			SSLContext sslContext = SSLContext.getInstance("TLSv1");
			sslContext.init(null, new TrustManager[] { trustManager }, new SecureRandom());
			
			URL url = new URL(sb.toString());
			HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
			urlConnection.setSSLSocketFactory(sslContext.getSocketFactory());

			
			InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());

			int read;
			char[] buff = new char[1024];
			while ((read = in.read(buff)) != -1)
			{
				jsonResults.append(buff, 0, read);
			}
		} catch (MalformedURLException e)
		{
			Log.e("RequestCities", "Error processing Places API URL");
			e.printStackTrace();
			return null;
		} catch (IOException e)
		{
			Log.e("RequestCities", "Error connecting to Places API");
			e.printStackTrace();
			return null;
		} catch (Exception e)
		{
			e.printStackTrace();		
		} finally
		{
			if (conn != null)
			{
				conn.disconnect();
			}
		}
		return jsonResults.toString();
	}

}
