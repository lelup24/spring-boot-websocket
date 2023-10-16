/*
 * This file is generated by jOOQ.
 */
package de.kipper.websocket.data.tables.daos;


import de.kipper.websocket.data.tables.UserSession;
import de.kipper.websocket.data.tables.records.UserSessionRecord;

import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Repository
public class UserSessionDao extends DAOImpl<UserSessionRecord, de.kipper.websocket.data.tables.pojos.UserSession, String> {

    /**
     * Create a new UserSessionDao without any configuration
     */
    public UserSessionDao() {
        super(UserSession.USER_SESSION, de.kipper.websocket.data.tables.pojos.UserSession.class);
    }

    /**
     * Create a new UserSessionDao with an attached configuration
     */
    @Autowired
    public UserSessionDao(Configuration configuration) {
        super(UserSession.USER_SESSION, de.kipper.websocket.data.tables.pojos.UserSession.class, configuration);
    }

    @Override
    public String getId(de.kipper.websocket.data.tables.pojos.UserSession object) {
        return object.getPrimaryId();
    }

    /**
     * Fetch records that have <code>primary_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchRangeOfPrimaryId(String lowerInclusive, String upperInclusive) {
        return fetchRange(UserSession.USER_SESSION.PRIMARY_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>primary_id IN (values)</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchByPrimaryId(String... values) {
        return fetch(UserSession.USER_SESSION.PRIMARY_ID, values);
    }

    /**
     * Fetch a unique record that has <code>primary_id = value</code>
     */
    public de.kipper.websocket.data.tables.pojos.UserSession fetchOneByPrimaryId(String value) {
        return fetchOne(UserSession.USER_SESSION.PRIMARY_ID, value);
    }

    /**
     * Fetch a unique record that has <code>primary_id = value</code>
     */
    public Optional<de.kipper.websocket.data.tables.pojos.UserSession> fetchOptionalByPrimaryId(String value) {
        return fetchOptional(UserSession.USER_SESSION.PRIMARY_ID, value);
    }

    /**
     * Fetch records that have <code>session_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchRangeOfSessionId(String lowerInclusive, String upperInclusive) {
        return fetchRange(UserSession.USER_SESSION.SESSION_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>session_id IN (values)</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchBySessionId(String... values) {
        return fetch(UserSession.USER_SESSION.SESSION_ID, values);
    }

    /**
     * Fetch records that have <code>creation_time BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchRangeOfCreationTime(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(UserSession.USER_SESSION.CREATION_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>creation_time IN (values)</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchByCreationTime(Long... values) {
        return fetch(UserSession.USER_SESSION.CREATION_TIME, values);
    }

    /**
     * Fetch records that have <code>last_access_time BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchRangeOfLastAccessTime(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(UserSession.USER_SESSION.LAST_ACCESS_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_access_time IN (values)</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchByLastAccessTime(Long... values) {
        return fetch(UserSession.USER_SESSION.LAST_ACCESS_TIME, values);
    }

    /**
     * Fetch records that have <code>max_inactive_interval BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchRangeOfMaxInactiveInterval(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(UserSession.USER_SESSION.MAX_INACTIVE_INTERVAL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>max_inactive_interval IN (values)</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchByMaxInactiveInterval(Integer... values) {
        return fetch(UserSession.USER_SESSION.MAX_INACTIVE_INTERVAL, values);
    }

    /**
     * Fetch records that have <code>expiry_time BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchRangeOfExpiryTime(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(UserSession.USER_SESSION.EXPIRY_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>expiry_time IN (values)</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchByExpiryTime(Long... values) {
        return fetch(UserSession.USER_SESSION.EXPIRY_TIME, values);
    }

    /**
     * Fetch records that have <code>principal_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchRangeOfPrincipalName(String lowerInclusive, String upperInclusive) {
        return fetchRange(UserSession.USER_SESSION.PRINCIPAL_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>principal_name IN (values)</code>
     */
    public List<de.kipper.websocket.data.tables.pojos.UserSession> fetchByPrincipalName(String... values) {
        return fetch(UserSession.USER_SESSION.PRINCIPAL_NAME, values);
    }
}