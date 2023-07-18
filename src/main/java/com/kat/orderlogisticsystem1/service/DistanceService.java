package com.kat.orderlogisticsystem1.service;

import java.io.IOException;


public interface DistanceService
{
	int calculateDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude)
			throws IOException, Exception;
}
