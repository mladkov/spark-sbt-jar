# spark-sbt-jar
Sample Databricks SBT based spark application.

This example makes reference to the `dbutils` API available within Databricks
as well.

## Create JAR job

JAR jobs allow us to leverage the `dbutils` library. Jobs specified with
`spark-submit` do not, since `spark-submit` jobs do not run in the context
of Notebooks, and hence know nothing about `dbutils`.

To create a JAR job, we leverage the Databricks CLI
([Azure](https://docs.azuredatabricks.net/user-guide/dev-tools/databricks-cli.html#id1)
/ [AWS](https://docs.databricks.com/user-guide/dev-tools/index.html)).

The following is an example `json` representing a job.  Put this into a file
such as `mladen-job.json`, create the job, then run it.

### Define the Job

In our job definition, note that we specify our jar with our driver code as
a jar in our list of libraries.

```json
{
  "name": "Mladen jar3",
  "new_cluster": {
    "spark_version": "5.2.x-scala2.11",
    "node_type_id": "Standard_DS3_v2",
    "enable_elastic_disk": true,
    "num_workers": 2
  },
  "libraries": [
    {
      "jar": "dbfs:/FileStore/mladen-jars/spark-sbt-jar_2.11-0.1.jar"
    }
  ],
  "email_notifications": {
    "on_start": [],
    "on_success": [],
    "on_failure": []
  },
  "timeout_seconds": 3600,
  "max_retries": 1,
  "schedule": {
    "quartz_cron_expression": "0 */10 12 ? * *",
    "timezone_id": "America/New_York"
  },
  "spark_jar_task": {
    "main_class_name": "com.databricks.examples.ProjectDriver"
  }
}
```

### Create the Job

Now we can create the job to run in our workspace:

```
databricks jobs create --json-file mladen-job.json
```

And you may jot down the `job-id` you get as a result. Here is our example.

```
{
  "job_id": 1373
}
```

### Run the job

Run the job by referencing the `job_id`.

```
databricks jobs run-now --job-id 1373
```
