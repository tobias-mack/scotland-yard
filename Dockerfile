# this dockerfile doesnt work, as the gui needs to be disabled
# this is only for display, for a working docker implementation, go to the 13_Docker branch

FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
RUN apt-get update && apt-get install -y libxrender1 libxtst6 libxi6 libgl1-mesa-glx
EXPOSE 8080
ADD . /scotland-yard
CMD sbt run -ti