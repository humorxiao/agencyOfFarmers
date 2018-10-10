package scau.zxck.service.market;

import scau.zxck.base.exception.BaseException;

import scau.zxck.entity.market.Userke;


public interface IUserkeService {


  Userke findById(String id) throws BaseException;
}
