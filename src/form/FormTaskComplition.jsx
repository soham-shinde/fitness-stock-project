import React, { useEffect, useState } from 'react'
import { addTaskToUser, getUserTaskById } from '../api-client/api-module';

export default function FormTaskComplition({ setSelect, trainerData, userDate }) {
    const [taskList, setTaskList] = useState();
    const [dataRender, setDataRender] = useState()
    useEffect(() => {
        fetchData();
    }, [dataRender]);



    const fetchData = async () => {
        try {
            const d = await getUserTaskById(userDate.id);
            setTaskList(d);

        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    function handelClose(params) {
        setSelect('');
    }
    return (
        <div>
            <div id="equipmentAddModal" className="modal">
                <div className="modal-content entity-add-form">
                    <span className="close" onClick={handelClose}>&times;</span>
                    <section className="task-list-section">
                        <h2>Task List</h2>
                        <ul className="task-list">
                            {taskList && taskList.map(item => {

                                return (
                                    <TaskCard key={item.id} data={item} setDataRender={setDataRender} />
                                )
                            }
                            )}
                        </ul>
                    </section>

                </div>
            </div>
        </div>
    )
}


function TaskCard({ data, setDataRender }) {

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

    const handleSubmit = async (e) => {

        const formData = {
            "id": data.id,
            "taskTrainer": data.taskTrainer,
            "taskUser": data.taskUser,
            "duration": data.duration,
            "workoutDate": data.workoutDate.split('T')[0],
            "workout": data.workout,
            "calories": data.calories,
            "isCompleted": true,
            "diet": data.diet
        }
        console.log(formData);

        await addTaskToUser(formData).then(() => {
            console.log("Sadfsd");
            setDataRender(data.id)
        });

    };

    return (

        <li className="task-item">
            <span className="task-name">{data.workout}</span>
            <span className="task-name">{data.diet}</span>
            <span className="task-name">{data.calories} kcal</span>
            <span className="task-name">{convertDateFormat(data.workoutDate.split('T')[0])}</span>
            <span className="task-duration">{data.duration} hr</span>
            {!data.iscompleted&&<button className="mark-completed-button" onClick={handleSubmit}>Mark Completed</button>}
        </li>

    )
}
