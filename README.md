# user-experior-coding
This User experior coding problem designed as per problem statement - 
https://docs.google.com/document/d/14QV5OF5RbFJHplW85er-8wyLgVdsKGByLZYAvzE4tko/edit


#### Run
```
mvn spring-boot:run
```
> default port - 8888
> 
#### Notes

- `act-process-dir` - used for passing the activity json dir default is /var/act-track
- `src/main/resources/schema.sql` - Postgres Schema file


### APis


|Name|	Path  |
|--|--|
| process activity tracking dir |	`/actTrack/process`  |
| Get previous week + todays report|	`/actTrack/weeklyReport`  |