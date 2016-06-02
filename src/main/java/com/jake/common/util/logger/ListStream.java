package com.jake.common.util.logger;

import java.util.List;

/**
 * List流
 * Created by Jake on 5/15 0015.
 */
public interface ListStream {


    public List<Object> getResults();


    public ListStream select(String param, Filter filter);


    public MapStream groupBy(String column);


    public int count();


    public int sum(String column);


    public ListStream join(ListStream listStream);

}
