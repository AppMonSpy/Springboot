package com.appspy.udiscover;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.appspy.udiscover.model.PriceUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.HTTPServer;

public class ShareValuePrometheus {

	private static double rand(double min, double max) {
		return min + (Math.random() * (max - min));
	}

	public static double getShare() {
		double shareValue = 0;
		try {
			String url="https://www.google.com/async/finance_wholepage_price_updates?ei=9bvrXrDmH5Tg-gTPwIKgCw&rlz=1C1GCEA_enUS900US900&yv=3&dfsl=1&async=mids:%2Fg%2F1dtybkr2,currencies:,_fmt:jspb";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				String jsonValue = response.toString().substring(4);
				ObjectMapper mapper = new ObjectMapper();
				PriceUpdate priceUpdate = mapper.readValue(jsonValue, PriceUpdate.class);
				List required = (List)priceUpdate.getPriceUpdate().get(0).get(0).get(0).get(17);
				System.out.println(required.get(3));
				shareValue = Double.parseDouble(required.get(3).toString());
			} else {
				System.out.println("Some error has happened.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return shareValue;
	}
	public static void main(String args[]) {
		Gauge gauge = Gauge.build().namespace("share_market").name("gnc_g").help("gauge").register();
		Thread bgThread = new Thread(() -> {
			while (true) {
				try {
					gauge.set(getShare());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		bgThread.start();
		try {
			HTTPServer server = new HTTPServer(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main1(String[] args) {
		Counter counter = Counter.build().namespace("java").name("my_counter").help("This is my counter").register();
		Gauge gauge = Gauge.build().namespace("java").name("my_gauge").help("This is my gauge").register();
		Histogram histogram = Histogram.build().namespace("java").name("my_histogram").help("This is my histogram").register();
		Summary summary = Summary.build().namespace("java").name("my_summary").help("This is my summary").register();

		Thread bgThread = new Thread(() -> {
			while (true) {
				try {
					counter.inc(rand(0, 5));
					gauge.set(rand(-5, 10));
					histogram.observe(rand(0, 5));
					summary.observe(rand(0, 5));


					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		bgThread.start();

		try {

			HTTPServer server = new HTTPServer(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}