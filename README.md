# Create-Delete-Remove-on-File-Data-Store

The data store will support the following functional requirements.

1. It can be initialized using an optional file path. If one is not provided, it will reliably create itself in a reasonable location on the laptop.

2. A new key value pair can be added to the data store using the Create operation. The keyis always a string - capped at 32chars. The value is always a SON abject - capped at 16KB 

3. if Create is invoked for an existing key, an appropriate error must be returned.

4. A Read operation on a key can be performed by providing the key, and roceiving thevalue in response, as a JSON object

5. A Delete operation can be performed by providing the key.

6. Appropriate error responses must always be returned to a client if it uses the data store in unexpected ways or breaches any limits.

The data store will also support the following non-functional requirements.

1. The size of the file storing data must never exceed 1GB. 

2. More than one client process cannot be allowed to use the same file as a data store at any given time.

3. A client process is allowed to access the data store using multiple threads, if it desires to The data store must therefore be the sale 

4. The client will bear a little memory costs as possible to use this data store, while deriving maximum performance with respect to response times for accessing the datastore

