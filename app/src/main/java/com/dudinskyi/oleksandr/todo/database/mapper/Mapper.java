package com.dudinskyi.oleksandr.todo.database.mapper;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
interface Mapper<From, To> {

    To mapFrom(From from);

    From mapTo(To from);
}
