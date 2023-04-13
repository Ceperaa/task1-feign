# Project Spring boot и gradle

Контроллер который принимает на вход Json Метод POST
 ```sh
 POST localhost:8081/clients
```

```sh
{
    "operatorLogin": "U_M17Z0",
    "clientPhones": {
        "phones": [
            "79809056564"
        ]
    },
    "clientInfo": {
        "app": {
            "cus": "ZAGYZK",
            "callerId": "sfa",
            "callerEntityId": "ABR-FW-SCRMFW-WORK-ACCOUNT ACP-36471",
            "workflow": "office-sms",
            "id": "YcsjUeaE2QAB%2BHmD",
            "sessionId": "Ycvr7+aE2QAB+HnX",
            "sessionPhone": ""
        },
        "clientDetails": {
            "checks": {
                "restrictWithoutConflictExplained": false,
                "restrictWithoutTIN": false,
                "restrictServicePackage": false,
                "restrictAccountOpening": false,
                "restrictAccountCurrencies": [],
                "checks": [
                    {
                        "checkCode": "N06",
                        "description": "Не передана дата окончания ДУЛ",
                        "checkResultCode": "PBS0105",
                        "partial": false,
                        "incorrect": false,
                        "document": {
                            "type": "025",
                            "number": "4643643",
                            "date": "2018-12-04",
                            "series": "",
                            "termDate": null
                        },
                        "clients": null,
                        "countryCode": null,
                        "riskFlags": null,
                        "attributes": null
                    },
{
                        "checkCode": "N05",
                        "description": "Не передана дата окончания ДУЛ",
                        "checkResultCode": "PBS0105",
                        "partial": false,
                        "incorrect": false,
                        "document": {
                            "type": "025",
                            "number": "4643643",
                            "date": "2018-12-04",
                            "series": "",
                            "termDate": null
                        },
                        "clients": null,
                        "countryCode": null,
                        "riskFlags": null,
                        "attributes": null
                    },
{
                        "checkCode": "N05",
                        "description": "OK",
                        "checkResultCode": "123",
                        "partial": true,
                        "incorrect": null,
                        "document": {
                            "type": "025",
                            "number": "4643643",
                            "date": "2018-12-04",
                            "series": "",
                            "termDate": null
                        },
                        "clients": null,
                        "countryCode": null,
                        "riskFlags": null,
                        "attributes": null
                    }
                ]
            }
        }
    }
}

```



- Из полученного JSON’a достаем массив объектов
```sh
clientInfo.clientDetails.checks.checks
```
-  Фильтруем его по
```sh
 checkCode = N05
```
-  С помощью **FeignClient** отправляем запросы  (запросов будет столько, сколько объектов в объекте checks):
```sh
 GET localhost:8080/check
```
- В хедер **“authorization”** вложино значение **“1234567890”**
- Ответом от сервиса checks будет объект со статусами
  **“OK”, ”ERROR”**
-  В тестах с помощью **Wiremock** проверено количество вызовов нашего метода по отправке запросов.
- В местах потенциального **NullPointerException** обернуто в **Optional** и выкидывается своя ошибка **FieldNullException**
- Обрабатываютя эти ошибки с помощью **ExceptionHandler** и выкидывается статус **510**.
  В тесте так же эмулирована эта ошибка и проверена, что вернулся нужный статус.
-  Используя **mapstruct** создана и протестирована новая сущность состоящая из объекта **clientInfo.app** и полей:
```sh
  restrictWithoutConflictExplained
  restrictWithoutTIN
  operatorLogin
```