package com.github.cc3002.citricliquid.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface IHandler extends PropertyChangeListener {
    boolean equals(Object o);
    void propertyChange(PropertyChangeEvent evt);
}
