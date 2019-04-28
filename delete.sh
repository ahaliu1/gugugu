#!/usr/bin/env bash
con="mysql -h193.112.77.221 -p3306 -uweb_class_member  -pweb_class_123 web_class_db"
${con} -e "show tables">>sql
eval(awk -v con="${con}" '{if(NR>1)printf("%s -e truncate table %s",con,$0)}' sql)
rm sql
