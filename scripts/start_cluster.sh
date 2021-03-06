# On master: run ssh-keygen accepting default options
$ ssh-keygen -t dsa
Enter file in which to save the key (/home/you/.ssh/id_dsa): [ENTER]
Enter passphrase (empty for no passphrase): [EMPTY]
Enter same passphrase again: [EMPTY]

# On workers:
# copy ~/.ssh/id_dsa.pub from your master to the worker, then use:
$ cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
$ chmod 644 ~/.ssh/authorized_keys

//submit app
spark-submit --master spark://masternode:7077 yourapp

# run shell on a cluster
spark-shell --master spark://masternode:7077
pyspark --master spark://masternode:7077

# running app on a cluster managed by YARN
export HADOOP_CONF_DIR="..."
spark-submit --master yarn yourapp

# the same with Mesos
spark-submit --master mesos://masternode:5050 yourapp 
//to run in resource restrictive mode use this
--conf spark.mesos.coarse=true
//more relaxed fine-grained is set to on by default