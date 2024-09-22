import redis

# Connect to the Redis server (assuming it's running on localhost and default port 6379)
r = redis.Redis(host='localhost', port=6379, decode_responses=True)

# Create a Redis hash called 'test' with fields 'count' and 'name'
r.hset("test", "count", 1)
r.hset("test", "name", "Fester Bestertester")

# Retrieve and print all the fields for the hash 'test'
test_data = r.hgetall("test")
print(test_data)