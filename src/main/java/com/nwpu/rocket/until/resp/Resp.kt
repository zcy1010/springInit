package com.nwpu.rocket.until.resp;

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

abstract class RespData(val succeed: Boolean)
private class SucceedRespData(val data: Any?) : RespData(true)
private class FailedRespData(val err_code: String, val err_msg: String) : RespData(false)

open class Resp(val data: RespData, val state: HttpStatus) :
        ResponseEntity<RespData>(data, state)

class RespSucceed(data: Any?) : Resp(SucceedRespData(data), HttpStatus.OK)
class RespFailed(errCode: String, errMsg: String) : Resp(FailedRespData(errCode, errMsg), HttpStatus.OK)
