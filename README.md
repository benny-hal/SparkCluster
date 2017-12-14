# SparkCluster
An example of using a Docker images to create a Spark Cluster and submit an App.

## Requirements
1. [Docker](https://docs.docker.com/engine/installation/) 1.10.0+ and [docker-compose](https://docs.docker.com/compose/) 1.6.0+
2. [SBT](http://www.scala-sbt.org/)

## Running
1. Create the app jar by executing the following command inside the ``sbt-application`` directory:

```sbt assembly```

2. Run the Spark cluster by executing the following command from the top directory:

```docker-compose up master slave```

This will run two images: 
- Spark master
- Spark slave

3. Run the Spark app by executing the following command from the top directory

```docker-compose up hello-world```

This will use a docker container to submit an app to the Spark cluster

The master’s web UI can be found at http://localhost:8080