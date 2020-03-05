package com.example.soccerallianceapp;

import com.example.soccerallianceapp.pojo.CreateSchedule.ScheduleMatch;
import com.example.soccerallianceapp.pojo.League;
import com.example.soccerallianceapp.pojo.ListOfLeaguesByCountry.ListOfLeaguesByCountry;

import com.example.soccerallianceapp.pojo.MatchScore_PlayedMatchStatastics.MatchScorePlayedmatchStatastics;

import com.example.soccerallianceapp.pojo.PlayedMatchListDashboard.PlayedmatchListDashboard;
import com.example.soccerallianceapp.pojo.Player;
import com.example.soccerallianceapp.pojo.PlayerDetail.PlayerDetail;
import com.example.soccerallianceapp.pojo.Team;
import com.example.soccerallianceapp.pojo.UpcomingMatchByLeague.UpcomingMatchByLeague;
import com.example.soccerallianceapp.pojo.User;
import com.example.soccerallianceapp.pojo.ViewPlayerListByTeamDashboard.ViewPlayerListDashboard;
import com.example.soccerallianceapp.pojo.ViewTeamDetail.ViewTeamDetail;
import com.example.soccerallianceapp.pojo.ViewTeamListByLeague.ViewTeamListByLeague;
import com.example.soccerallianceapp.pojo.ViewTeamListDashboard.ViewTeamList;
import com.example.soccerallianceapp.pojo.leaguelistbyuserId.LeaguesFragment;
import com.example.soccerallianceapp.pojo.listLeagueDashboard.ListLeagueDashboard;
import com.example.soccerallianceapp.pojo.listOfCountries.ListOfCountries;
import com.example.soccerallianceapp.pojo.matchListDashboard.MatchListDashboard;
import com.example.soccerallianceapp.pojo.matchScore.MatchScoreDisplay;
import com.example.soccerallianceapp.pojo.viewTeamListFromLeagueId.ViewTeamListFromLeagueId;
import com.example.soccerallianceapp.pojo.viewregisteruserdetail.ViewregisterUserDetail;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Getdataservice {

//    @GET("{geoId}")
//    Call<Weather> getWeather(@Path("geoId")int geoId);



    //@FormUrlEncoded
    @Headers({"Content-Type:application/json"})
    @POST("registerUser")

    Call<User> registerUser(@Body User user);




    @POST("UpdateUserProfile")
    Call<User> UpdateUserProfile(@Body User user);



    @GET("ViewregisterUserDetail&{uid}")
    Call<ViewregisterUserDetail> ViewregisterUserDetail(

            @Path("uid") String uid
    );

    @GET("listOfLeague_guestDashboard")
    Call<ListLeagueDashboard> getListOfLeague_guestDashboardCall();


    @GET("ListOfCountries")
    Call<ListOfCountries> getListOfCountriesCall();


    @GET("viewTeamListFromLeagueId&{league_id}")
    Call<ViewTeamListByLeague> getviewTeamListFromLeagueIdCall(@Path("league_id") String league_id);

    @GET("LeagueListByUser&{user_id}")
    Call<LeaguesFragment> getLeagueList(@Path("user_id") String user_id);

    @GET("matchScore&{matchid}&{teamid}")
    Call<MatchScoreDisplay> getMatchScores(@Path("matchid") int matchid, @Path("teamid") int teamid);

    @GET("UpdateMatchScore&{match_id}&{team_id1}&{goal1}&{shots1}&{shots_on_target1}&{possession1}&{passes1}&{pass_accuracy1}&{fouls1}&{yellow_cards1}&{red_cards1}&{offsides1}&{corners1}&{team_id2}&{goal2}&{shots2}&{shots_on_target2}&{possession2}&{passes2}&{pass_accuracy2}&{fouls2}&{yellow_cards2}&{red_cards2}&{offsides2}&{corners2}")
    Call<ResponseBody> updateMatchScore(@Path("match_id") int Match_Id, @Path("team_id1") int Team_Id1,
                                        @Path("goal1") int Goal1, @Path("shots1") int Shots1,
                                        @Path("shots_on_target1") int Shots_On_Target1, @Path("possession1") int Possession1,
                                        @Path("passes1") int Passes1, @Path("pass_accuracy1") int Pass_Accuracy1,
                                        @Path("fouls1") int Fouls1, @Path("yellow_cards1") int Yellow_Cards1,
                                        @Path("red_cards1") int Red_Cards1, @Path("offsides1") int Offsides1,
                                        @Path("corners1") int Corners1, @Path("team_id2") int Team_Id2, @Path("goal2") int Goal2,
                                        @Path("shots2") int Shots2, @Path("shots_on_target2") int Shots_On_Target2,
                                        @Path("possession2") int Possession2, @Path("passes2") int Passes2,
                                        @Path("pass_accuracy2") int Pass_Accuracy2, @Path("fouls2") int Fouls2,
                                        @Path("yellow_cards2") int Yellow_Cards2, @Path("red_cards2") int Red_Cards2,
                                        @Path("offsides2") int Offsides2, @Path("corners2") int Corners2);



    @GET("viewTeamListFromLeagueId&{League_id}")
    Call<ViewTeamListFromLeagueId> viewTeamListFromLeagueId(@Path("League_id") int League_id);

    @GET("viewTeamList")
    Call<ViewTeamList> getviewTeamListCall();


    @POST("CreateSchedule")
    Call<ScheduleMatch> SCHEDULE_MATCH_CALL(@Body ScheduleMatch schedulematch);

   @GET("upcomingMatches_guestDashboard")
   Call<MatchListDashboard> getupcomingMatches_guestDashboardCall();

    @GET("matchScore&{match_id}&{team_id}")
    Call<MatchScorePlayedmatchStatastics> getMatchscore(@Path("match_id") int match_id ,@Path("team_id") int team_id );


    @GET("playedMatches_guestDashboard")
    Call<PlayedmatchListDashboard> getplayedMatches_guestDashboardCall();


    @GET("viewPlayerListFromTeam&{team_id}")
    Call<ViewPlayerListDashboard> getviewPlayerListFromTeamDashboardCall(@Path("team_id") int team_id);

    @GET("ListOfLeaguesByCountry&{country}")
    Call<ListOfLeaguesByCountry> getListOfLeaguesByCountryCall(@Path("country") String country);

    @GET("ViewTeamDetail&{uid}")
    Call<ViewTeamDetail> ViewTeamDetail(

            @Path("uid") String uid
    );


    @POST("CreateTeam")
    Call<Team> CreateTeam(@Body Team team);



    @POST("UpdateTeam")
    Call<Team> UpdateTeam(@Body Team team);



    @GET("viewPlayerListFromTeam&{team_id}")
    Call<Team> viewPlayerListFromTeam(

            @Path("team_id") int team_id
    );

    @POST("AddPlayerInTeam")
    Call<Player> AddPlayerInTeam(@Body Player player);


    @POST("createLeague")
    Call<League> createLeague(@Body League league);

    @GET("upcomingMatches_league&{league_id}")
    Call<UpcomingMatchByLeague> getUpcomingMatchesByLeague(

            @Path("league_id") int league_id
    );


    @GET("PlayerDetail&{player_id}")
    Call<PlayerDetail> PlayerDetail(@Path("player_id") int player_id);

    @POST("ModifyPlayerDetails")
    Call<Player> ModifyPlayerDetails(@Body Player player);

    @GET("removePlayerFromTeam&{player_id}&{team_id}")
    Call<ResponseBody> removePlayerFromTeam(@Path("player_id") int player_id,@Path("team_id") int team_id);



}

