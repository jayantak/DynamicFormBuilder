package com.glean.dynamicformbuilder;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    JSONOperations jsonOperations = new JSONOperations("dataFields.json", "dataOut.json");

    public boolean patternchecker(String patternstr, String input)
    {
        Pattern pattern = Pattern.compile(patternstr);


        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public int check(MongoOperations mongoOperations, Map userData) throws IOException {

        Collection formFields1=userData.keySet();
        Collection formvalues=userData.values();
//        System.out.println("formfields1 is " + formFields1.toArray()[0]);
//        System.out.println("formValues is "+ formvalues.toArray()[0]);

        boolean matchFound;
        int i,inputnum,min,max;
        String temp,maxi,input,value,type,necessary,validation;
        Object objattr;
        JSONObject jobjattr;
        String formFields,patternstr,inputval,mins,maxs;
        JSONObject attribute;
        JSONObject formStructure1;
        JSONOperations jops1 = new JSONOperations("dataFields.json", "dataOut.json");
    //    System.out.println("userdata is "+ userData);

        formStructure1 = mongoOperations.getFields();
        Set set =  formStructure1.keySet();
        Collection values1 =formStructure1.values();
     //  System.out.println("form structure is " + values1);

        Iterator iter = values1.iterator();


        while (iter.hasNext())
        {
            JSONParser parser = new JSONParser();
            Object obj;
            JSONObject jsonObj;
            String str = iter.next().toString();

            try {

                 obj = parser.parse(str);

                 jsonObj = (JSONObject) obj;
                System.out.println("json obj is" + jsonObj);

                Set set1 =jsonObj.keySet();

                Iterator iter1 = set1.iterator();

                while(iter.hasNext())
                {
                    formFields=iter1.next().toString();
                    temp=jsonObj.get(formFields).toString();

                    objattr=parser.parse(temp);
                    jobjattr=(JSONObject) objattr;

              //      System.out.println("jobjattr is " + jobjattr);
                    if(jobjattr.containsKey("maxlength")) {

                        maxi = jobjattr.get("maxlength").toString();
                        float maxf= Float.parseFloat(maxi);

                        inputval= getInput(formFields1,formFields,formvalues);


                        if(inputval.length()>maxf)
                            System.out.println("Value in " + formFields+" is too large");

                    }

                    if(jobjattr.containsKey("pattern"))
                    {
                        patternstr = jobjattr.get("pattern").toString();
                        inputval= getInput(formFields1, formFields, formvalues);
                        matchFound = patternchecker(patternstr, inputval);
                        if(!matchFound)
                            System.out.println("Value at " + formFields+ " does not obey the pattern");
                    }
                    if(jobjattr.containsKey("type"))
                    {
                        type = jobjattr.get("type").toString();
                        if(type.equals("email"))
                        {
                            patternstr = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                            inputval= getInput(formFields1,formFields,formvalues);
                            matchFound = patternchecker(patternstr, inputval);
                            if (!matchFound)
                                System.out.println("Invalid email ID");
                        }

                        if(type.equals("date")) {
                            patternstr = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
                            inputval= getInput(formFields1,formFields,formvalues);
                            matchFound = patternchecker(patternstr, inputval);

                            if(!matchFound)
                                System.out.println("Invalid email ID");

                        }

                        if(type.equals("number"))
                        {
                            inputval= getInput(formFields1,formFields,formvalues);
                            inputnum = Integer.parseInt(inputval);
                            if(jobjattr.containsKey("min"))
                            {
                                mins=jobjattr.get("min").toString();
                                min=Integer.parseInt(mins);
                                if(inputnum<min)
                                    System.out.println("Value for " + formFields + " has to be atleast "+ min);
                            }
                            if(jobjattr.containsKey("max"))
                            {
                                maxs=jobjattr.get("max").toString();
                                max=Integer.parseInt(maxs);
                                if(inputnum>max)
                                    System.out.println("Value for " + formFields + " has to be atmost "+ max);
                            }
                        }
                    }

                    if(jobjattr.containsKey("necessary"))
                    {
                        necessary = jobjattr.get("necessary").toString();
                        if(necessary.equals("YES")) {
                            inputval = getInput(formFields1, formFields, formvalues);
                            if(inputval.length()<1)
                                System.out.println(formFields + " field is necessary.");
                        }
                    }
                    //For datafieldsnew.json
                    if(jobjattr.containsKey("validation"))
                    {
                        validation = jobjattr.get("validation").toString();
                        if(validation.equals("noNumerals"))
                        {
                            inputval= getInput(formFields1,formFields,formvalues);
                            patternstr = "[a-zA-Z]+(\\s+[a-zA-Z]+)*";
                            matchFound = patternchecker(patternstr, inputval);

                            if(!matchFound)
                                System.out.println(formFields + " contains illegal characters");
                        }
                       else if(validation.equals("numerals"))
                        {
                            inputval= getInput(formFields1,formFields,formvalues);
                            patternstr = "[a-zA-Z0-9]+(\\s+[a-zA-Z0-9]+)*";
                            matchFound = patternchecker(patternstr, inputval);

                            if(!matchFound)
                                System.out.println(formFields + " contains illegal characters");
                        }
                    }
                }
            }
            catch(Exception e) {
                System.out.println("error is " + e.getMessage());
            }

       }



        return 1;
    }

    public String getInput(Collection formFields1, String formFields, Collection formvalues) {

        int i=0;
        while(true)
        {
            if(formFields1.toArray()[i].equals(formFields))
            {
                break;
            }
            i++;
        }

        return formvalues.toArray()[i].toString();
    }

}
