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

package it.airlines.manager.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import it.airlines.manager.model.Flight;
import it.airlines.manager.service.FlightLocalService;
import it.airlines.manager.service.persistence.CustomerPersistence;
import it.airlines.manager.service.persistence.FlightPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the flight local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link it.airlines.manager.service.impl.FlightLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see it.airlines.manager.service.impl.FlightLocalServiceImpl
 * @generated
 */
public abstract class FlightLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, FlightLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>FlightLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>it.airlines.manager.service.FlightLocalServiceUtil</code>.
	 */

	/**
	 * Adds the flight to the database. Also notifies the appropriate model listeners.
	 *
	 * @param flight the flight
	 * @return the flight that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Flight addFlight(Flight flight) {
		flight.setNew(true);

		return flightPersistence.update(flight);
	}

	/**
	 * Creates a new flight with the primary key. Does not add the flight to the database.
	 *
	 * @param flightId the primary key for the new flight
	 * @return the new flight
	 */
	@Override
	@Transactional(enabled = false)
	public Flight createFlight(long flightId) {
		return flightPersistence.create(flightId);
	}

	/**
	 * Deletes the flight with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param flightId the primary key of the flight
	 * @return the flight that was removed
	 * @throws PortalException if a flight with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Flight deleteFlight(long flightId) throws PortalException {
		return flightPersistence.remove(flightId);
	}

	/**
	 * Deletes the flight from the database. Also notifies the appropriate model listeners.
	 *
	 * @param flight the flight
	 * @return the flight that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Flight deleteFlight(Flight flight) {
		return flightPersistence.remove(flight);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			Flight.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return flightPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>it.airlines.manager.model.impl.FlightModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return flightPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>it.airlines.manager.model.impl.FlightModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return flightPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return flightPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return flightPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Flight fetchFlight(long flightId) {
		return flightPersistence.fetchByPrimaryKey(flightId);
	}

	/**
	 * Returns the flight matching the UUID and group.
	 *
	 * @param uuid the flight's UUID
	 * @param groupId the primary key of the group
	 * @return the matching flight, or <code>null</code> if a matching flight could not be found
	 */
	@Override
	public Flight fetchFlightByUuidAndGroupId(String uuid, long groupId) {
		return flightPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the flight with the primary key.
	 *
	 * @param flightId the primary key of the flight
	 * @return the flight
	 * @throws PortalException if a flight with the primary key could not be found
	 */
	@Override
	public Flight getFlight(long flightId) throws PortalException {
		return flightPersistence.findByPrimaryKey(flightId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(flightLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Flight.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("flightId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(flightLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Flight.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("flightId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(flightLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Flight.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("flightId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Flight>() {

				@Override
				public void performAction(Flight flight)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, flight);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(Flight.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return flightLocalService.deleteFlight((Flight)persistedModel);
	}

	public BasePersistence<Flight> getBasePersistence() {
		return flightPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return flightPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the flights matching the UUID and company.
	 *
	 * @param uuid the UUID of the flights
	 * @param companyId the primary key of the company
	 * @return the matching flights, or an empty list if no matches were found
	 */
	@Override
	public List<Flight> getFlightsByUuidAndCompanyId(
		String uuid, long companyId) {

		return flightPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of flights matching the UUID and company.
	 *
	 * @param uuid the UUID of the flights
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of flights
	 * @param end the upper bound of the range of flights (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching flights, or an empty list if no matches were found
	 */
	@Override
	public List<Flight> getFlightsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Flight> orderByComparator) {

		return flightPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the flight matching the UUID and group.
	 *
	 * @param uuid the flight's UUID
	 * @param groupId the primary key of the group
	 * @return the matching flight
	 * @throws PortalException if a matching flight could not be found
	 */
	@Override
	public Flight getFlightByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return flightPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the flights.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>it.airlines.manager.model.impl.FlightModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of flights
	 * @param end the upper bound of the range of flights (not inclusive)
	 * @return the range of flights
	 */
	@Override
	public List<Flight> getFlights(int start, int end) {
		return flightPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of flights.
	 *
	 * @return the number of flights
	 */
	@Override
	public int getFlightsCount() {
		return flightPersistence.countAll();
	}

	/**
	 * Updates the flight in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param flight the flight
	 * @return the flight that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Flight updateFlight(Flight flight) {
		return flightPersistence.update(flight);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			FlightLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		flightLocalService = (FlightLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FlightLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Flight.class;
	}

	protected String getModelClassName() {
		return Flight.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = flightPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@Reference
	protected CustomerPersistence customerPersistence;

	protected FlightLocalService flightLocalService;

	@Reference
	protected FlightPersistence flightPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetTagLocalService
		assetTagLocalService;

}