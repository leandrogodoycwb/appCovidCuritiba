/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.LuizCzaikowski.APICovid19;

import java.util.List;

/**
 *
 * @author Saulo Jr
 */
public interface RepositoryInterface<K,T> {
  
    public List<T> all();
    public void Media();
    public void Moda (T entidade);
    public void DezDesc();
}
