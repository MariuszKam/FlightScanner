package com.solvd.interfaces;

import com.solvd.repairserviceclasses.Computer;

@FunctionalInterface
public interface RepairActionLambda<T extends Computer> {
    void performRepairAction(T computer);
}
