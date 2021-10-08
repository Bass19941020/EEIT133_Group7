document.getElementById("id_Name").addEventListener('blur',
()=>{
        //輸入
        let object_Name = document.getElementById("id_Name");
        let value_Name = object_Name.value;
        //輸出
        let object_SpanName = document.getElementById("id_SpanName");

        if(value_Name!=""&&value_Name.length>=2){
            for (let index = 0; index < value_Name.length; index++) {
                let unicode = value_Name.charCodeAt(index);
                if (unicode>=0x4e00 && unicode<=0x9fff) {
                    object_SpanName.className="ok";
                    return object_SpanName.innerHTML="格式正確";
                }else{
                    object_SpanName.className="no";
                    return object_SpanName.innerHTML="<img src='img/error.png'>必須為中文";
                }
            }
        }else if(value_Name!==""){
            object_SpanName.className="no";
            return object_SpanName.innerHTML="<img src='img/error.png'>至少兩個字";
        }else{
            object_SpanName.className="no";
            return object_SpanName.innerHTML="<img src='img/error.png'>*必填";
        }
    });

document.getElementById("id_Act").addEventListener('blur',
    ()=>{
            let object_Act =document.getElementById("id_Act");
            let value_Act =object_Act.value;
            let object_SpanAct = document.getElementById("id_SpanAct");
            let re =/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
            if (re.test(value_Act)) {
                object_SpanAct.className="ok";
                return object_SpanAct.innerHTML="合法帳號";
            } else if(value_Act==""){
                object_SpanAct.className="no";
                return object_SpanAct.innerHTML="<img src='img/error.png'>*必填";
            } else {
                object_SpanAct.className="no";
                return object_SpanAct.innerHTML="<img src='img/error.png'>錯誤格式<br>請輸入至少六位 & 包含英文大小寫 & 數字的密碼";
            }
        });

document.getElementById("id_Pwd").addEventListener('blur',
()=>{
        let object_Pwd =document.getElementById("id_Pwd");
        let value_Pwd =object_Pwd.value;
        let object_SpanPwd = document.getElementById("id_SpanPwd");
        let re =/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
        if (re.test(value_Pwd)) {
            object_SpanPwd.className="ok";
            return object_SpanPwd.innerHTML="合法密碼";
        } else if(value_Pwd==""){
            object_SpanPwd.className="no";
            return object_SpanPwd.innerHTML="<img src='img/error.png'>*必填";
        } else {
            object_SpanPwd.className="no";
            return object_SpanPwd.innerHTML="<img src='img/error.png'>錯誤格式<br>請輸入至少六位 & 包含英文大小寫 & 數字的密碼";
        }
    });

    



document.getElementById("id_Date").addEventListener('blur',
()=>{
    //取得元素
    let element_Date = document.getElementById("id_Date");
    let element_SpanDate = document.getElementById("id_SpanDate");
    //取得值
    let value_Date = element_Date.value;
    let re = /^(\d{4})-(\d{1,2})-(\d{1,2})$/
    
	if (re.test(value_Date)) { 
       let aaray_Date = value_Date.split("-"); //使用者原始
       let userDay = new Date(value_Date);//系統幫我判斷是否有這天，沒有就nan
       let userDate =userDay.getDate();//取系統判斷的日期，如果不正確就是nan。
        if(aaray_Date[2]==userDate){ //如果正常的話，轉出的日期物件應該等於原始資料
            element_SpanDate.className="ok";
            return element_SpanDate.innerHTML="格式正確";    
        }else{
            element_SpanDate.className="no";
            return element_SpanDate.innerHTML="<img src='img/error.png'>沒有這一天!!";    
        }
	} 
	else if(value_Date==""){
        element_SpanDate.className="no";
        return element_SpanDate.innerHTML="<img src='img/error.png'>*必填!!";    
    }else{
        element_SpanDate.className="no";
		return element_SpanDate.innerHTML="<img src='img/error.png'>格式不正確";

    }
});




