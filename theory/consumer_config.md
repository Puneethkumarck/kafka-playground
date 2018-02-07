Kafka consumer-config
====================
- Important(Mostly Used) Consumer Configs are
   - <b>bootstrap.servers</b> -> A list of host/port pairs to use for establishing the initial connection to the Kafka cluster
   - <b>enable.auto.commit</b> -> If this property set to true then consumer's offset will be periodically committed in the  background.
   - <b>auto.commit.interval.ms</b> -> The frequency in milliseconds that the consumer offsets are auto-committed to Kafka if      <code>enable.auto.commit</code> is set to <code>true</code>.
   - <b>session.timeout.ms</b> -> The timeout used to detect consumer failures when using Kafka's group management facility       The consumer sends periodic heartbeats to indicate its liveness to the broker. If no heartbeats are received by the           broker before the expiration of this session timeout then the broker will remove this consumer from the group and             initiate a rebalance. Note that the value must be in the allowable range as configured in the broker configuration by         <code>group.min.session.timeout.ms</code> and <code>group.max.session.timeout.ms</code>.
   - key.deserializer -> Deserializer class for key that implements the <code>Deserializer</code> interface.
   - value.deserializer -> Deserializer class for value that implements the <code>Deserializer</code> interface.
   - group.id ->A unique string that identifies the consumer group this consumer belongs to. This property is required if the       consumer uses either the group management functionality by using <code>subscribe(topic)</code> or the Kafka-based             offset management strategy.
   - max.poll.records -> The maximum number of records returned in a single call to poll().
   - auto.offset.reset -> What to do when there is no initial offset in Kafka or if the current offset does not exist any more       on the server (e.g. because that data has been deleted): <ul><li>earliest: automatically reset the offset to the               earliest offset<li>latest: automatically reset the offset to the latest offset</li><li>none: throw exception to the         consumer if no previous offset is found for the consumer's group</li><li>anything else: throw exception to the consumer.        </li></ul>

