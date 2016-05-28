package com.jake.common.util.logger;

import java.util.Map;

/**
 * Created by Jake on 5/15 0015.
 */
public interface MapStream {


    public Map<Object, Object> getResults();


    public MapStream select(String param, Filter filter);


    public int count();


    public int sum(String column);


    public ListStream toList();

}
