package com.skywaet.resourcepool.factories;

public interface ResourceFactory <T>{
    T create();
    T destroy();
}
