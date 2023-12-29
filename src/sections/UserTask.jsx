import React, { useEffect, useState } from 'react'
import { getUserTaskListById } from '../api-client/api-module';

export default function UserTask({userId}) {

    const [dataList, setDataList] = useState();

    useEffect(() => {
        fetchData();
    }, []);



    const fetchData = async () => {
        try {
            const list = await getUserTaskListById(userId);
            setDataList(list);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };


    return (
        <>
            <h2>Assigned Tasks and Supplements</h2>
            <div className="tasks-cards">
                <div className="tasks-card overflow-scroll-parent" style={{height:"90%"}}>
                    <h3>Assigned Tasks</h3>
                    <ul className="task-list overflow-scroll">

                    {dataList && dataList.map(item => {
                        return (
                            <ListItem key={item.id} data={item}/>
                        )
                    }
                    )}
                    </ul>
                </div>
            </div>
        </>
    )
}
function convertDateFormat(inputDate) {
    var parts = inputDate.split("-");
    if (parts.length === 3) {
      var year = parts[0];
      var month = parts[1];
      var day = parts[2];
      return day + "-" + month + "-" + year;
    } else {
      return "Invalid date format";
    }
  }
function ListItem({data}) {
    return (
        <li className="task-item">
            <span className="task-name">{data.workout}</span>
            <span className="task-name">{data.diet}</span>
            <span className="task-name">{data.calories} kcal</span>
            <span className="task-name">{convertDateFormat(data.workoutDate.split('T')[0])}</span>
            <span className="task-duration">{data.duration} hr</span>
        </li>
    )
}