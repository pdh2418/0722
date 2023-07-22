package com.yubin.apsfc.service;

import com.yubin.apsfc.dean.Admin;

public interface AdminService {

    void showmin();

    boolean exist(int id);

    int delect(int id);

    int modif(int id);

    int addmin(Admin admin);
}
