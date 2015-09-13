val contactsContactLists = validSigns.distinct().mapPartitions{
  signs =>
  val mapper = createMapper()
  val client = new HttpClient()
  client.start()
  // create http request
  signs.map {sign =>
    createExchangeForSign(sign)
 // fetch responses
  }.map{ case (sign, exchange) =>
      (sign, readExchangeCallLog(mapper, exchange))
  }.filter(x => x._2 != null) // Remove empty CallLogs
}

