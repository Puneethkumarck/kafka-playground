CREATE keyspace if NOT exists login WITH replication = {'class' : 'SimpleStrategy','replication_factor': 1 };

CREATE TABLE IF NOT exists login.login_history(
userId text,
createdAt text,
location text,
channel text,
device text,
loginCount varint,
PRIMARY KEY(userId,location)
);

