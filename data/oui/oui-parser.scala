spark.conf.set("spark.sql.crossJoin.enabled", true)

val p = """^([0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2})\s{1,}\(hex\)\t\t(.*)$""".r
val oui = sc.textFile("/Users/hilios/Downloads/oui.txt").map {
  case p(prefix, name) => Some((prefix.replaceAll("-", ":"), name))
  case _ => None
}.filter(_.nonEmpty).map(_.get).toDF("prefix",  "name")

oui.coalesce(1).write.csv("/Users/hilios/Documents/oui-parsed.csv")

val manufactures = sc.textFile("/Users/hilios/Downloads/mobile-manufactures.txt")
  .filter(_.nonEmpty).toDF("manufacturer")

val phones = manufactures.join(oui, $"name".startsWith($"manufacturer")).select("prefix", "name")
phones.coalesce(1).write.csv("/Users/hilios/Documents/phones.csv")
