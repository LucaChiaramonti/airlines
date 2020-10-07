/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package it.airlines.manager.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Flight service. Represents a row in the &quot;AM_Flight&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>it.airlines.manager.model.impl.FlightModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>it.airlines.manager.model.impl.FlightImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Flight
 * @generated
 */
@ProviderType
public interface FlightModel
	extends BaseModel<Flight>, GroupedModel, ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a flight model instance should use the {@link Flight} interface instead.
	 */

	/**
	 * Returns the primary key of this flight.
	 *
	 * @return the primary key of this flight
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this flight.
	 *
	 * @param primaryKey the primary key of this flight
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this flight.
	 *
	 * @return the uuid of this flight
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this flight.
	 *
	 * @param uuid the uuid of this flight
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the flight ID of this flight.
	 *
	 * @return the flight ID of this flight
	 */
	public long getFlightId();

	/**
	 * Sets the flight ID of this flight.
	 *
	 * @param flightId the flight ID of this flight
	 */
	public void setFlightId(long flightId);

	/**
	 * Returns the group ID of this flight.
	 *
	 * @return the group ID of this flight
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this flight.
	 *
	 * @param groupId the group ID of this flight
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this flight.
	 *
	 * @return the company ID of this flight
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this flight.
	 *
	 * @param companyId the company ID of this flight
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this flight.
	 *
	 * @return the user ID of this flight
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this flight.
	 *
	 * @param userId the user ID of this flight
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this flight.
	 *
	 * @return the user uuid of this flight
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this flight.
	 *
	 * @param userUuid the user uuid of this flight
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this flight.
	 *
	 * @return the user name of this flight
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this flight.
	 *
	 * @param userName the user name of this flight
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this flight.
	 *
	 * @return the create date of this flight
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this flight.
	 *
	 * @param createDate the create date of this flight
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this flight.
	 *
	 * @return the modified date of this flight
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this flight.
	 *
	 * @param modifiedDate the modified date of this flight
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the departure date of this flight.
	 *
	 * @return the departure date of this flight
	 */
	@AutoEscape
	public String getDepartureDate();

	/**
	 * Sets the departure date of this flight.
	 *
	 * @param departureDate the departure date of this flight
	 */
	public void setDepartureDate(String departureDate);

	/**
	 * Returns the arrival date of this flight.
	 *
	 * @return the arrival date of this flight
	 */
	@AutoEscape
	public String getArrivalDate();

	/**
	 * Sets the arrival date of this flight.
	 *
	 * @param arrivalDate the arrival date of this flight
	 */
	public void setArrivalDate(String arrivalDate);

	/**
	 * Returns the departure time of this flight.
	 *
	 * @return the departure time of this flight
	 */
	@AutoEscape
	public String getDepartureTime();

	/**
	 * Sets the departure time of this flight.
	 *
	 * @param departureTime the departure time of this flight
	 */
	public void setDepartureTime(String departureTime);

	/**
	 * Returns the arrival time of this flight.
	 *
	 * @return the arrival time of this flight
	 */
	@AutoEscape
	public String getArrivalTime();

	/**
	 * Sets the arrival time of this flight.
	 *
	 * @param arrivalTime the arrival time of this flight
	 */
	public void setArrivalTime(String arrivalTime);

	/**
	 * Returns the departure airport of this flight.
	 *
	 * @return the departure airport of this flight
	 */
	@AutoEscape
	public String getDepartureAirport();

	/**
	 * Sets the departure airport of this flight.
	 *
	 * @param departureAirport the departure airport of this flight
	 */
	public void setDepartureAirport(String departureAirport);

	/**
	 * Returns the arrival airport of this flight.
	 *
	 * @return the arrival airport of this flight
	 */
	@AutoEscape
	public String getArrivalAirport();

	/**
	 * Sets the arrival airport of this flight.
	 *
	 * @param arrivalAirport the arrival airport of this flight
	 */
	public void setArrivalAirport(String arrivalAirport);

	/**
	 * Returns the available places of this flight.
	 *
	 * @return the available places of this flight
	 */
	@AutoEscape
	public String getAvailablePlaces();

	/**
	 * Sets the available places of this flight.
	 *
	 * @param availablePlaces the available places of this flight
	 */
	public void setAvailablePlaces(String availablePlaces);

	/**
	 * Returns the total places of this flight.
	 *
	 * @return the total places of this flight
	 */
	@AutoEscape
	public String getTotalPlaces();

	/**
	 * Sets the total places of this flight.
	 *
	 * @param totalPlaces the total places of this flight
	 */
	public void setTotalPlaces(String totalPlaces);

	/**
	 * Returns the duration of this flight.
	 *
	 * @return the duration of this flight
	 */
	@AutoEscape
	public String getDuration();

	/**
	 * Sets the duration of this flight.
	 *
	 * @param duration the duration of this flight
	 */
	public void setDuration(String duration);

	/**
	 * Returns the price of this flight.
	 *
	 * @return the price of this flight
	 */
	@AutoEscape
	public String getPrice();

	/**
	 * Sets the price of this flight.
	 *
	 * @param price the price of this flight
	 */
	public void setPrice(String price);

}