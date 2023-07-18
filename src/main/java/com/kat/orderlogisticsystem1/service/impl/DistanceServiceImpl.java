package com.kat.orderlogisticsystem1.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kat.orderlogisticsystem1.service.DistanceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
public class DistanceServiceImpl implements DistanceService
{
	@Value("${google.maps.apikey}")
	private String apiKey;

	public int calculateDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude)
			throws IOException, Exception
	{
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?"
				+ "origins=" + startLatitude + "," + startLongitude
				+ "&destinations=" + endLatitude + "," + endLongitude
				+ "&key=" + apiKey;
		//https://developers.google.com/maps/documentation/distance-matrix/overview#DistanceMatrixRequests
		//The total distance of this route, expressed in meters (value) and as text. The textual value uses the unit system specified with the unit parameter of the original request, or the origin's region.
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
		if (HttpStatus.OK.equals(entity.getStatusCode()))
		{
			JsonNode root = new ObjectMapper().readTree(entity.getBody());
			int distance = root.path("rows").get(0).path("elements").get(0).path("distance").path("value").asInt();
			return distance;
		}
		else
		{
			throw new Exception("Error getting distance from Google Maps API");
		}
	}
}