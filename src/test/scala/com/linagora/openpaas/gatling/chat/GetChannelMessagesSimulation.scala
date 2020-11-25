package com.linagora.openpaas.gatling.chat

import com.linagora.openpaas.gatling.Configuration._
import com.linagora.openpaas.gatling.chat.ChannelsSteps._
import com.linagora.openpaas.gatling.core.LoginSteps.login
import com.linagora.openpaas.gatling.core.UserSteps.getProfile
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class GetChannelMessagesSimulation extends Simulation {
  private val feeder = csv("users.csv")

  val scn = scenario("Testing OpenPaaS chat channel messages retrieval")
    .feed(feeder.circular())
    .exec(login)
    .exec(getProfile())
    .exec(createChannel())
    .pause(1 second)
    .exec(listChannels())
    .pause(1 second)
    .during(ScenarioDuration) {
      exec(listChannels())
        .pause(1 second)
        .exec(pickOneChannel)
        .exec(getChannelMessages)
        .pause(1 second)
    }

  setUp(scn.inject(rampUsers(UserCount) during(InjectDuration))).protocols(httpProtocol)
}