package com.school.school.mapper;

public interface IMapper<I, O> {
  public O map(I in);
}
