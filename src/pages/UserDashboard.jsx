import React, { useEffect, useState } from 'react'
import NavBar from '../component/NavBar';
import UserProgress from '../sections/UserProgress';
import UserTask from '../sections/UserTask';
import UserTrainer from '../sections/UserTrainer';
import UserMember from '../sections/UserMember';
import UserStoreHis from '../sections/UserStoreHis';
import { useParams } from 'react-router-dom';
import { getCustomerById } from '../api-client/api-module';

export default function UserDashboard() {

    const [option, setOption] = useState('progress');
    const {userId } = useParams();
    const [user, setUser] = useState();
    const [submenuDisplay, setSubmenuDisplay] = useState(true)

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const list = await getCustomerById(userId);
            console.log(list);
            setUser(list);
        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    return (
        <>
        <NavBar/>
        {user&&
            <div className="body-container">
                <div className="a-container">
                    <div className="siderbar-menu">
                    <div className='menu-toggle' id='menu-toggle' onClick={() => {
                            console.log(submenuDisplay);
                            setSubmenuDisplay(!submenuDisplay)

                        }}>Menu <img src="/img/line-angle-down-icon.svg" alt="" /></div>
                        <ul className={`w ${submenuDisplay?'show':''}`}>
                            <li>
                                <div className={`menu-option ${option === 'progress' ? 'active-menu' : ''}`} onClick={() => { setSubmenuDisplay(!submenuDisplay) ;setOption('progress')}}>
                                    Progress
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                            <li>
                                <div className={`menu-option ${option === 'tasks' ? 'active-menu' : ''}`} onClick={() => { setSubmenuDisplay(!submenuDisplay) ;setOption('tasks')}}>
                                    Tasks
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                            <li>
                                <div className={`menu-option ${option === 'trainer' ? 'active-menu' : ''}`} onClick={() => { setSubmenuDisplay(!submenuDisplay) ;setOption('trainer')} }>
                                    Trainer
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                            <li>
                                <div className={`menu-option ${option === 'membership' ? 'active-menu' : ''}`} onClick={() => { setSubmenuDisplay(!submenuDisplay) ;setOption('membership')}}>
                                    Membership
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                            <li>
                                <div className={`menu-option ${option === 'Purchase' ? 'active-menu' : ''}`} onClick={() => { setSubmenuDisplay(!submenuDisplay) ;setOption('Purchase')} }>
                                    Store Purchase
                                    <img src="img/line-angle-right-icon.svg" alt='' className="toggle-btn" />
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div className="main-container" id="mainContainer">
                    {option==='progress'&&<UserProgress user={user}/>}
                    {option==='tasks'&&<UserTask userId={user.id}/>}
                    {option==='trainer'&&<UserTrainer pid={user.userPlansPurchase}/>}
                    {option==='membership'&&<UserMember pid={user.userPlansPurchase}/>}
                    {option==='Purchase'&&<UserStoreHis userId={user.id}/>}
                    </div>
                </div>
            </div>  }
            <footer>
        <div className="divider-y"></div>
        <p>&copy; 2023 Fitsta.com All rights reserved.</p>

    </footer>
        </>
    )
}
