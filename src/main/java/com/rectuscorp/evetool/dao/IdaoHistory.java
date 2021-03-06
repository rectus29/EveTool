package com.rectuscorp.evetool.dao;

import com.rectuscorp.evetool.entities.core.History;
import com.rectuscorp.evetool.entities.core.User;

import java.util.Date;
import java.util.List;

/**
 * User: rectus_29
 * Date: 4 févr. 2011
 * Time: 11:46:47
 */
public interface IdaoHistory {
    public List getHistoriesByCriteria(Date d, User u, String a, Long i, String t);
    public List getHistories(int first, int count, String sortProperty, boolean sortAsc);
    public History getLastHistory(User u, String a, Long i, String t);
}
