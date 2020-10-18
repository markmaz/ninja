package com.ninjarmm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ninjarmm.utils.DBUtils;
import org.jooq.Record;
import org.jooq.Result;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;

public class PagedResults<T>
{
    @JsonProperty("totalResults")
    Integer total;

    @JsonProperty("size")
    Integer size;

    @JsonProperty("page")
    Integer page;

    @JsonProperty("values")
    List<T> values;

    public PagedResults(int page, int size, Integer total, List<T> values)
    {
        this.total = total;
        this.values = values;
        this.size = size;
        this.page = page;
    }

    public Integer getCount()
    {
        return total;
    }

    public PagedResults<T> setCount(Integer value)
    {
        total = value;
        return this;
    }

    public Integer getSize()
    {
        return size;
    }

    public PagedResults<T> setSize(Integer value)
    {
        size = value;
        return this;
    }

    public Integer getPage()
    {
        return page;
    }

    public PagedResults<T> setPage(Integer value)
    {
        page = value;
        return this;
    }

    public List<T> getValues()
    {
        return values;
    }

    public PagedResults<T> setValues(List<T> values)
    {
        this.values = values;
        return this;
    }

    public static <T2> PagedResults<T2> of(List<T2> values, int total, int page, int size)
    {
        if (values.size() > total)
            throw new InvalidParameterException("total must be >= values.size()");

        return new PagedResults<T2>(page, size, total, values);
    }

    public static <E> PagedResults<E> forResult(Result<Record> result, java.lang.Class<? extends E> cls, int size, int page)
    {
        if(result.isNotEmpty())
            return PagedResults.of(result.into(cls), result.get(0).get(DBUtils.getCountOverField(), int.class), page, size);
        else
            return PagedResults.empty(cls);
    }

    @SafeVarargs
    public static <T2> PagedResults<T2> of(T2... items)
    {
        return new PagedResults<T2>(0, items.length, items.length, List.of(items));
    }

    public static <E> PagedResults<E> empty(java.lang.Class<? extends E> cls)
    {
        return new PagedResults<E>(0, 0, 0, List.of());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagedResults<?> pagedResults1 = (PagedResults<?>) o;
        return Objects.equals(total, pagedResults1.total) &&
                Objects.equals(size, pagedResults1.size) &&
                Objects.equals(page, pagedResults1.page) &&
                Objects.equals(values, pagedResults1.values);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(total, size, page, values);
    }
}

