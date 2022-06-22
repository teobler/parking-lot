
### AC 停车场有空位可以停车, 获得停车票
 Given parkingLot with 1 empty parkingLot lot When parkingLot a car Then this car should be parked and return a ticket.


### AC 停车位已满，给『停车场车位已满』 提示
 Given parkingLot with 0 parkingLot lot When parkingLot a car Then this car should show "停车场车位已满".
 Given parkingLot with 1 parkingLot lot When parkingLot 2 car Then second car should show "停车场车位已满".


### AC 可以根据有效的票据取车
 Given a valid ticket When pick up a car from parkingLot lot Then return a car


### AC 用过的票 或者 无效票，提示『无效票』
 Given a used ticket When pick up a car from parkingLot lot Then show a tip "无效票"
 Given an invalid ticket When pick up a car from parkingLot lot Then show a tip "无效票"
