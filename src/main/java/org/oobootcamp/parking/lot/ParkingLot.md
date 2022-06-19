##AC 停车场车位数量可配置
tasking1 given Parking Lot Number when Creating Parking then return Parking With Specific Number Of Parking Lot


##AC 停车场有空位可以停车
tasking given Parking With 1 Empty Parking Lot when Parking A Car then this car should be parked.


##AC 停车位已满，给『停车场车位已满』 提示
tasking given Parking With 0 Parking Lot when Parking A Car then this car should show "停车场车位已满".
tasking given Parking With 1 Parking Lot when Parking 2 Car then second car should show "停车场车位已满".


##AC 车辆有唯一标识



##AC 停车时会有票据
tasking given Parking With 1 Empty Parking Lot when Parking A Car then return a ticket.



##AC 可以根据有效的票据取车
tasking given a valid ticket when pick up a car from parking lot then return a car






##AC 无效票，提示『无效票』
tasking given an invalid ticket when pick up a car from parking lot then show a tip "无效票"

