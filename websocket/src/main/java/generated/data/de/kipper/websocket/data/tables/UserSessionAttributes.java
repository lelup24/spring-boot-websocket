/*
 * This file is generated by jOOQ.
 */
package de.kipper.websocket.data.tables;


import de.kipper.websocket.data.Keys;
import de.kipper.websocket.data.Public;
import de.kipper.websocket.data.tables.records.UserSessionAttributesRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserSessionAttributes extends TableImpl<UserSessionAttributesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.user_session_attributes</code>
     */
    public static final UserSessionAttributes USER_SESSION_ATTRIBUTES = new UserSessionAttributes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserSessionAttributesRecord> getRecordType() {
        return UserSessionAttributesRecord.class;
    }

    /**
     * The column
     * <code>public.user_session_attributes.session_primary_id</code>.
     */
    public final TableField<UserSessionAttributesRecord, String> SESSION_PRIMARY_ID = createField(DSL.name("session_primary_id"), SQLDataType.CHAR(36).nullable(false), this, "");

    /**
     * The column <code>public.user_session_attributes.attribute_name</code>.
     */
    public final TableField<UserSessionAttributesRecord, String> ATTRIBUTE_NAME = createField(DSL.name("attribute_name"), SQLDataType.VARCHAR(200).nullable(false), this, "");

    /**
     * The column <code>public.user_session_attributes.attribute_bytes</code>.
     */
    public final TableField<UserSessionAttributesRecord, byte[]> ATTRIBUTE_BYTES = createField(DSL.name("attribute_bytes"), SQLDataType.BLOB.nullable(false), this, "");

    private UserSessionAttributes(Name alias, Table<UserSessionAttributesRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserSessionAttributes(Name alias, Table<UserSessionAttributesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.user_session_attributes</code> table
     * reference
     */
    public UserSessionAttributes(String alias) {
        this(DSL.name(alias), USER_SESSION_ATTRIBUTES);
    }

    /**
     * Create an aliased <code>public.user_session_attributes</code> table
     * reference
     */
    public UserSessionAttributes(Name alias) {
        this(alias, USER_SESSION_ATTRIBUTES);
    }

    /**
     * Create a <code>public.user_session_attributes</code> table reference
     */
    public UserSessionAttributes() {
        this(DSL.name("user_session_attributes"), null);
    }

    public <O extends Record> UserSessionAttributes(Table<O> child, ForeignKey<O, UserSessionAttributesRecord> key) {
        super(child, key, USER_SESSION_ATTRIBUTES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<UserSessionAttributesRecord> getPrimaryKey() {
        return Keys.SPRING_SESSION_ATTRIBUTES_PK;
    }

    @Override
    public List<ForeignKey<UserSessionAttributesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.USER_SESSION_ATTRIBUTES__SPRING_SESSION_ATTRIBUTES_FK);
    }

    private transient UserSession _userSession;

    /**
     * Get the implicit join path to the <code>public.user_session</code> table.
     */
    public UserSession userSession() {
        if (_userSession == null)
            _userSession = new UserSession(this, Keys.USER_SESSION_ATTRIBUTES__SPRING_SESSION_ATTRIBUTES_FK);

        return _userSession;
    }

    @Override
    public UserSessionAttributes as(String alias) {
        return new UserSessionAttributes(DSL.name(alias), this);
    }

    @Override
    public UserSessionAttributes as(Name alias) {
        return new UserSessionAttributes(alias, this);
    }

    @Override
    public UserSessionAttributes as(Table<?> alias) {
        return new UserSessionAttributes(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserSessionAttributes rename(String name) {
        return new UserSessionAttributes(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserSessionAttributes rename(Name name) {
        return new UserSessionAttributes(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserSessionAttributes rename(Table<?> name) {
        return new UserSessionAttributes(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, byte[]> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super String, ? super String, ? super byte[], ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super String, ? super String, ? super byte[], ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}