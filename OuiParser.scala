spark.conf.set("spark.sql.crossJoin.enabled", true)
import org.apache.spark.sql._

val basePath = "/Users/hilios/Sites/wifi-apc-analysis/data/oui"

val p = """^([0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2})\s{1,}\(hex\)\t\t(.*)$""".r
val oui = sc.textFile(s"$basePath/oui.txt").map {
  case p(prefix, name) => Some((prefix.replaceAll("-", ":"), name))
  case _ => None
}.filter(_.nonEmpty).map(_.get).toDF("prefix",  "name")

oui.coalesce(1).write.csv(s"$basePath/oui-parsed.csv")

val manufactures = spark.read.csv(s"$basePath/manufactures.csv").toDF("manufacturer", "count")

val phones = manufactures.join(oui, upper($"name").contains($"manufacturer") || lower($"name").contains("mobile")).select("prefix", "name").distinct()
phones.coalesce(1).write.csv(s"$basePath/phones.csv")
