# Distributed UID

A 64-bit numeric UID ordered sequentially by timestamp


## Approach

It mostly follows the twitter's snowflake ID approach.
The ID is composed of
Timestamp - 41 bits
Node ID (MAC address hash) - 10 bits
Sequence number - 13 bits 


