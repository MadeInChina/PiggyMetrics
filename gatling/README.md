# Docker Gatling 2.3.1

    docker build -t registry.docker.com/gatling:2.3.1 .

    docker run --rm registry.docker.com/gatling:2.3.1 computerdatabase.BasicSimulation

    docker run --rm -v $(pwd)/results:/gatling/results registry.docker.com/gatling:2.3.1 computerdatabase.BasicSimulation

    docker run --rm -v $(pwd)/results:/gatling/results -v $(pwd)/user-files:/gatling/user-files registry.docker.com/gatling:2.3.1 sample.BasicSimulation

### Example directories

    Service
     |_ test
        	|_ gatling
                |_ body
                |_ data
                |_ simulations

    docker run --rm -v $(pwd)/results:/gatling/results -v $(pwd)/test/gatling:/gatling/user-files gatling sample.BasicSimulation
