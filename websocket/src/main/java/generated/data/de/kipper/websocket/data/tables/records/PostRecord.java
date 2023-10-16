/*
 * This file is generated by jOOQ.
 */
package de.kipper.websocket.data.tables.records;


import de.kipper.websocket.data.tables.Post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PostRecord extends UpdatableRecordImpl<PostRecord> implements Record7<UUID, String, String, LocalDateTime, UUID, LocalDateTime, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.post.id</code>.
     */
    public PostRecord setId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.post.id</code>.
     */
    @NotNull
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.post.title</code>.
     */
    public PostRecord setTitle(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.post.title</code>.
     */
    @Size(max = 255)
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.post.content</code>.
     */
    public PostRecord setContent(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.post.content</code>.
     */
    public String getContent() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.post.created_at</code>.
     */
    public PostRecord setCreatedAt(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.post.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>public.post.created_by</code>.
     */
    public PostRecord setCreatedBy(UUID value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.post.created_by</code>.
     */
    public UUID getCreatedBy() {
        return (UUID) get(4);
    }

    /**
     * Setter for <code>public.post.updated_at</code>.
     */
    public PostRecord setUpdatedAt(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.post.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>public.post.updated_by</code>.
     */
    public PostRecord setUpdatedBy(UUID value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>public.post.updated_by</code>.
     */
    public UUID getUpdatedBy() {
        return (UUID) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<UUID, String, String, LocalDateTime, UUID, LocalDateTime, UUID> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<UUID, String, String, LocalDateTime, UUID, LocalDateTime, UUID> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Post.POST.ID;
    }

    @Override
    public Field<String> field2() {
        return Post.POST.TITLE;
    }

    @Override
    public Field<String> field3() {
        return Post.POST.CONTENT;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Post.POST.CREATED_AT;
    }

    @Override
    public Field<UUID> field5() {
        return Post.POST.CREATED_BY;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return Post.POST.UPDATED_AT;
    }

    @Override
    public Field<UUID> field7() {
        return Post.POST.UPDATED_BY;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public String component3() {
        return getContent();
    }

    @Override
    public LocalDateTime component4() {
        return getCreatedAt();
    }

    @Override
    public UUID component5() {
        return getCreatedBy();
    }

    @Override
    public LocalDateTime component6() {
        return getUpdatedAt();
    }

    @Override
    public UUID component7() {
        return getUpdatedBy();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public String value3() {
        return getContent();
    }

    @Override
    public LocalDateTime value4() {
        return getCreatedAt();
    }

    @Override
    public UUID value5() {
        return getCreatedBy();
    }

    @Override
    public LocalDateTime value6() {
        return getUpdatedAt();
    }

    @Override
    public UUID value7() {
        return getUpdatedBy();
    }

    @Override
    public PostRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public PostRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public PostRecord value3(String value) {
        setContent(value);
        return this;
    }

    @Override
    public PostRecord value4(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public PostRecord value5(UUID value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public PostRecord value6(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public PostRecord value7(UUID value) {
        setUpdatedBy(value);
        return this;
    }

    @Override
    public PostRecord values(UUID value1, String value2, String value3, LocalDateTime value4, UUID value5, LocalDateTime value6, UUID value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PostRecord
     */
    public PostRecord() {
        super(Post.POST);
    }

    /**
     * Create a detached, initialised PostRecord
     */
    public PostRecord(UUID id, String title, String content, LocalDateTime createdAt, UUID createdBy, LocalDateTime updatedAt, UUID updatedBy) {
        super(Post.POST);

        setId(id);
        setTitle(title);
        setContent(content);
        setCreatedAt(createdAt);
        setCreatedBy(createdBy);
        setUpdatedAt(updatedAt);
        setUpdatedBy(updatedBy);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised PostRecord
     */
    public PostRecord(de.kipper.websocket.data.tables.pojos.Post value) {
        super(Post.POST);

        if (value != null) {
            setId(value.getId());
            setTitle(value.getTitle());
            setContent(value.getContent());
            setCreatedAt(value.getCreatedAt());
            setCreatedBy(value.getCreatedBy());
            setUpdatedAt(value.getUpdatedAt());
            setUpdatedBy(value.getUpdatedBy());
            resetChangedOnNotNull();
        }
    }
}