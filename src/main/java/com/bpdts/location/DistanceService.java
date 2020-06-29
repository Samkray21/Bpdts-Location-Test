package com.bpdts.location;

import java.util.Map;

public interface DistanceService {

    /**
     * Will get all people who are listed as either living in London, or whose current
     * coordinates are within 50 miles of London
     *
     * @return all people
     */
    Map<Integer, User> getAllUsers();

}
