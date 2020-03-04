package io.turntabl.employementprofilingsystem.Utilities;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Parser {

    public Map<String, Object> validate_params(Map<String, Object> requestData, List<String> requiredParams){

        Map<String, Object> response = new HashMap<>();
        try{

            List<String> missingParams = new ArrayList<>();

            for (String param: requiredParams){
                if(requestData.get(param) == null || !requestData.containsKey(param) || requestData.get(param).equals("") || requestData.get(param).equals(new ArrayList<>())){
                    missingParams.add(param);
                }
            }

            if(missingParams.size() > 0){
                String result = String.join(",", missingParams);
                response.put("code","02");
                response.put("msg","Missing and/or blank parameters: " + result);
            }else{
                response.put("code","00");
                response.put("msg","Valid Params");
            }

        }catch (Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");
        }

        return response;
    }

}