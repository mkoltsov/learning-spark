input = sc.parallelize([1,2,3,4,5,6])
result = input.map(lambda x:x*x).collect()

for num in result:
	print "%i" % (num)

