# MongoDB_Java
###### 这是关于Java连接MongoDB进行操作的小Demo

## 实现一个基于MongoDB的商品管理系统

#### 三层架构
###### 1.web层

- 负责界面显示 
###### 2.service层

- 负责业务逻辑处理
###### 3.dao层

- 负责MongoDB数据库接口

#### domain层

- 负责定义商品类

#### MongoDBUtil 工具类

- 实现Java对MongoDB操作的封装

#### Propreties 属性文件

- 设置MongoDB的配置信息

#### MongoDB数据
 
        var productArr=[
        {
            pid: NumberInt(1),
            pname: "Lenovo",
            price: 5000
        },
        {
            pid: NumberInt(2),
            pname: "Haier",
            price: 3000
        },
        {
            pid: NumberInt(3),
            pname: "Thor",
            price: 5000
        },
        {
            pid: NumberInt(4),
            pname: "Nike",
            price: 6000
        },
        {
            pid: NumberInt(5),
            pname: "Dior",
            price: 2000
        },
        {
            pid: NumberInt(6),
            pname: "Hermes",
            price: 2400
        },
        {
            pid: NumberInt(7),
            pname: "CK",
            price: 7000
        },
        {
            pid: NumberInt(8),
            pname: "Chanel",
            price: 2000
        },
        {
            pid: NumberInt(9),
            pname: "BMW",
            price: 50000
        }]
        for(var i = 0; i<productArr.length; i++){
            db.products.insert(productArr[i])
        }
        插入到数据库db = productDB
