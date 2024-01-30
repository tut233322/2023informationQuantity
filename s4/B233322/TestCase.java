package s4.B233322; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;
import s4.slow.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity, 
}                        
*/


public class TestCase {
    static boolean success = true;

    private static void frequencer_valid_case_test(String space, String target) {
	FrequencerInterface myObject = new s4.B233322.Frequencer();
	FrequencerInterface slowObject = new s4.slow.Frequencer();
        myObject.setSpace(space.getBytes());
        slowObject.setSpace(space.getBytes());
        myObject.setTarget(target.getBytes());
        slowObject.setTarget(target.getBytes());
	int correct_freq = slowObject.frequency();
	int freq = myObject.frequency();
	assert correct_freq == freq : "frequencer test failed: space is " + space + ", target is " + target + ", so return value must be " + correct_freq + ", but it was " + freq + ".";
    }
    private static void estimator_valid_case_test(String space, String target) {
	s4.slow.InformationEstimator slowObject = new s4.slow.InformationEstimator();
	InformationEstimator myObject = new s4.B233322.InformationEstimator();
        slowObject.setSpace(space.getBytes());
        myObject.setSpace(space.getBytes());
        slowObject.setTarget(target.getBytes());
        myObject.setTarget(target.getBytes());
	double correct_esti = slowObject.estimation();
	double esti = myObject.estimation();
	assert Math.abs(correct_esti - esti) < 0.0001 : "estimator test failed: space is " + space + ", target is " + target + ", so return value must be " + correct_esti + ", but it was " + esti + ".";
    }

    public static void main(String[] args) {
	try {
	    FrequencerInterface  myObject;
	    int freq;
	    System.out.println("checking Frequencer");

	    // This is smoke test
	    myObject = new Frequencer();
	    myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    myObject.setTarget("H".getBytes());
	    freq = myObject.frequency();
	    assert freq == 4: "Hi Ho Hi Ho, H: " + freq;
        
	    // Write your testCase here
       	    // Test case 1: target is not set
            myObject = new Frequencer();
            myObject.setSpace("Hi Ho Hi Ho".getBytes());
            freq = myObject.frequency();
            assert freq == -1 : "test failed: when target not set, return value must be -1. but return value is " + freq + ".";
        
            // Test case 2: target length is zero
            myObject = new Frequencer();
            myObject.setSpace("Hi Ho Hi Ho".getBytes());
            myObject.setTarget("".getBytes());
            freq = myObject.frequency();
            assert freq == -1 : "test failed: when target length is zero, return value must be -1. but return value is " + freq + ".";
        
            // Test case 3: space is not set
            myObject = new Frequencer();
            myObject.setTarget("H".getBytes());
            freq = myObject.frequency();
            assert freq == 0 : "test failed: when space is not set, return value must be 0. but return value is " + freq + ".";
        
            // Test case 4: space length is zero
            myObject = new Frequencer();
            myObject.setSpace("".getBytes());
            myObject.setTarget("H".getBytes());
            freq = myObject.frequency();
            assert freq == 0 : "test failed: when space length is zero, return value must be 0. but return value is " + freq + ".";
        
            // Test case 5: valid case
            frequencer_valid_case_test("Hi Ho Hi Ho", "H");
            frequencer_valid_case_test("Hi Ho Hi Ho", "X");
            frequencer_valid_case_test("Hi Ho Hi Ho", "Hi");
            frequencer_valid_case_test("H", "H");
            frequencer_valid_case_test("H", "Ha");
            frequencer_valid_case_test("aaaaaaaaaa", "a");
            frequencer_valid_case_test("aaaaaaaaaa", "aa");
            frequencer_valid_case_test("aaaaaaaaaa", "aaa");
            frequencer_valid_case_test("As", "H");
            frequencer_valid_case_test("Zip", "H");
            frequencer_valid_case_test("A", "Hi");
            frequencer_valid_case_test("Z", "Hi");
	}
	catch(Exception e) {
	    System.out.println("Exception occurred in Frequencer Object");
            e.printStackTrace();
	    success = false;
	}

	try {
	    InformationEstimatorInterface myObject;
	    double value;
	    System.out.println("checking InformationEstimator");
	    
	    // Test case 1: target is not set
            myObject = new InformationEstimator();
            myObject.setSpace("3210321001230123".getBytes());
            value = myObject.estimation();
            assert (value > -0.0001) && (0.0001 > value) : "test failed: when target not set, return value must be 0. but return value is " + value + ".";
	    
	    // Test case 2: target'length is zero
            myObject = new InformationEstimator();
            myObject.setSpace("3210321001230123".getBytes());
	    myObject.setTarget("".getBytes());
            value = myObject.estimation();
            assert (value > -0.0001) && (0.0001 > value) : "test failed: when target length is zero, return value must be 0. but return value is " + value + ".";
	    
	    // Test case 3: space is not set
            myObject = new InformationEstimator();
	    myObject.setTarget("0".getBytes());
            value = myObject.estimation();
            assert value == Double.MAX_VALUE : "test failed: when space is not set, return value must be " + Double.MAX_VALUE + ". but return value is " + value + ".";

	    /*   the comment does NOT mention the case where space length is zero
	    // Test case 4: space length is zero
            myObject = new InformationEstimator();
	    myObject.setSpace("".getBytes());
	    myObject.setTarget("0".getBytes());
            value = myObject.estimation();
            assert value == Double.MAX_VALUE : "test failed: when space length is zero, return value must be " + Double.MAX_VALUE + ". but return value is " + value + ".";
	    */
	    
	    // Test case 5: true value is infinite
            myObject = new InformationEstimator();
	    myObject.setSpace("abc".getBytes());
	    myObject.setTarget("0".getBytes());
            value = myObject.estimation();
            assert value == Double.MAX_VALUE : "test failed: when true value is infinite, return value must be " + Double.MAX_VALUE + ". but return value is " + value + ".";
	    
	    // Test case 6: valid case
            myObject = new InformationEstimator();
	    estimator_valid_case_test("3210321001230123", "0");
	    estimator_valid_case_test("3210321001230123", "01");
	    estimator_valid_case_test("3210321001230123", "0123");
	    estimator_valid_case_test("3210321001230123", "00");
	    String random_stuff500 = "OnceuponatimetherewasanoldSowwiththreelittlePigs,andasshehadnotenoughtokeepthem,shesentthemouttoseektheirfortune.shesentthemouttoseektheirfortunetheoldsowManwithabundleofstrawThefirstthatwentoffmetaManwithabundleofstraw,andsaidtohim,\"Please,Man,givemethatstrawtobuildmeahouse\";whichtheMandid,andthelittlePigbuiltahousewithit.PresentlycamealongaWolf,andknockedatthedoor,andsaid,\"LittlePig,littlePig,letmecomein.\"TowhichthePiganswered,\"No,no,bythehairofmychinnychinchin.\"\"ThenI'llhuffandI'llpuff,andI'";
	    estimator_valid_case_test(random_stuff500, "he");
	    estimator_valid_case_test(random_stuff500, "Pig");
	    estimator_valid_case_test(random_stuff500, "and");
	    estimator_valid_case_test(random_stuff500, "little");
	    String random_stuff5000 = "DorothylivedinthemidstofthegreatKansasprairies,withUncleHenry,whowasafarmer,andAuntEm,whowasthefarmer'swife.Theirhousewassmall,forthelumbertobuildithadtobecarriedbywagonmanymiles.Therewerefourwalls,afloorandaroof,whichmadeoneroom;andthisroomcontainedarustylookingcookstove,acupboardforthedishes,atable,threeorfourchairs,andthebeds.UncleHenryandAuntEmhadabigbedinonecorner,andDorothyalittlebedinanothercorner.Therewasnogarretatall,andnocellar-exceptasmallholedugintheground,calledacyclonecellar,wherethefamilycouldgoincaseoneofthosegreatwhirlwindsarose,mightyenoughtocrushanybuildinginitspath.Itwasreachedbyatrapdoorinthemiddleofthefloor,fromwhichaladderleddownintothesmall,darkhole.WhenDorothystoodinthedoorwayandlookedaround,shecouldseenothingbutthegreatgrayprairieoneveryside.Notatreenorahousebrokethebroadsweepofflatcountrythatreachedtotheedgeoftheskyinalldirections.Thesunhadbakedtheplowedlandintoagraymass,withlittlecracksrunningthroughit.Eventhegrasswasnotgreen,forthesunhadburnedthetopsofthelongbladesuntiltheywerethesamegraycolortobeseeneverywhere.Oncethehousehadbeenpainted,butthesunblisteredthepaintandtherainswasheditaway,andnowthehousewasasdullandgrayaseverythingelse.WhenAuntEmcametheretoliveshewasayoung,prettywife.Thesunandwindhadchangedher,too.Theyhadtakenthesparklefromhereyesandleftthemasobergray;theyhadtakentheredfromhercheeksandlips,andtheyweregrayalso.Shewasthinandgaunt,andneversmilednow.WhenDorothy,whowasanorphan,firstcametoher,AuntEmhadbeensostartledbythechild'slaughterthatshewouldscreamandpressherhanduponherheartwheneverDorothy'smerryvoicereachedherears;andshestilllookedatthelittlegirlwithwonderthatshecouldfindanythingtolaughat.UncleHenryneverlaughed.Heworkedhardfrommorningtillnightanddidnotknowwhatjoywas.Hewasgrayalso,fromhislongbeardtohisroughboots,andhelookedsternandsolemn,andrarelyspoke.ItwasTotothatmadeDorothylaugh,andsavedherfromgrowingasgrayasherothersurroundings.Totowasnotgray;hewasalittleblackdog,withlongsilkyhairandsmallblackeyesthattwinkledmerrilyoneithersideofhisfunny,weenose.Totoplayedalldaylong,andDorothyplayedwithhim,andlovedhimdearly.Today,however,theywerenotplaying.UncleHenrysatuponthedoorstepandlookedanxiouslyatthesky,whichwasevengrayerthanusual.DorothystoodinthedoorwithTotoinherarms,andlookedattheskytoo.AuntEmwaswashingthedishes.Fromthefarnorththeyheardalowwailofthewind,andUncleHenryandDorothycouldseewherethelonggrassbowedinwavesbeforethecomingstorm.Therenowcameasharpwhistlingintheairfromthesouth,andastheyturnedtheireyesthatwaytheysawripplesinthegrasscomingfromthatdirectionalso.SuddenlyUncleHenrystoodup.\"There'sacyclonecoming,Em,\"hecalledtohiswife.\"I'llgolookafterthestock.\"Thenherantowardtheshedswherethecowsandhorseswerekept.AuntEmdroppedherworkandcametothedoor.Oneglancetoldherofthedangercloseathand.\"Quick,Dorothy!\"shescreamed.\"Runforthecellar!\"TotojumpedoutofDorothy'sarmsandhidunderthebed,andthegirlstartedtogethim.AuntEm,badlyfrightened,threwopenthetrapdoorinthefloorandclimbeddowntheladderintothesmall,darkhole.DorothycaughtTotoatlastandstartedtofollowheraunt.Whenshewashalfwayacrosstheroomtherecameagreatshriekfromthewind,andthehouseshooksohardthatshelostherfootingandsatdownsuddenlyuponthefloor.Thenastrangethinghappened.Thehousewhirledaroundtwoorthreetimesandroseslowlythroughtheair.Dorothyfeltasifsheweregoingupinaballoon.Thenorthandsouthwindsmetwherethehousestood,andmadeittheexactcenterofthecyclone.Inthemiddleofacyclonetheairisgenerallystill,butthegreatpressureofthewindoneverysideofthehouseraisedituphigherandhigher,untilitwasattheverytopofthecyclone;andthereitremainedandwascarriedmilesandmilesawayaseasilyasyoucouldcarryafeather.Itwasverydark,andthewindhowledhorriblyaroundher,butDorothyfoundshewasridingquiteeasily.Afterthefirstfewwhirlsaround,andoneothertimewhenthehousetippedbadly,shefeltasifshewerebeingrockedgently,likeababyinacradle.Totodidnotlikeit.Heranabouttheroom,nowhere,nowthere,barkingloudly;butDorothysatquitestillonthefloorandwaitedtoseewhatwouldhappen.OnceTotogottooneartheopentrapdoor,andfellin;andatfirstthelittlegirlthoughtshehadlosthim.Butsoonshesawoneofhisearsstickingupthroughthehole,forthestrongpressureoftheairwaskeepinghimupsothathecouldnotfall.Shecrepttothehole,caughtTotobytheear,anddraggedhimintotheroomagain,afterwardclosingthetrapdoorsothatnomoreaccidentscouldhappen.Hourafterhourpassedaway,andslowlyDorothygotoverherfright;butshefeltquitelonely,andthewindshriekedsoloudlyallaboutherthatshenearlybecamedeaf.Atfirstshehadwonderedifshewouldbedashedtopieceswhenthehousefellagain;butasthehourspassedandnothingterriblehappened,shestoppedworryingandresolvedtowaitcalmlyandseewhatthefuturewouldbring.Atlastshecrawledovertheswayingfloortoherbed,andlaydownuponit;andTotofollowedandlaydownbesideher.Inspiteoftheswayingofthehouseandthewailingofthewind,Dorothysoonclosedhereyesandfellfastasleep.Shewasawakenedbyashock,sosuddenandseverethatifDorothyhadnotbeenlyingonthesoftbedshemighthavebeenhurt.Asitwas,thejarmadehercatchherbreathandwonderwhathadhappened;andTotoputhiscoldlittlenoseintoherfacean";
	    estimator_valid_case_test(random_stuff5000, "at");
	    estimator_valid_case_test(random_stuff5000, "that");
	    estimator_valid_case_test(random_stuff5000, "wherethe");
	    estimator_valid_case_test(random_stuff5000, "herethecowsandhorseswerekept");
	}
	catch(Exception e) {
	    System.out.println("Exception occurred in InformationEstimator Object");
            e.printStackTrace();
	    success = false;
	}
        if(success) { System.out.println("TestCase OK"); } 
    }
}	    
	    
