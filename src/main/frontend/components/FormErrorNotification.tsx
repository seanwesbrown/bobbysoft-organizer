export type FormErrorNotificationProps = {
  title: string;
  message: string;
};

export default function FormErrorNotification(props: FormErrorNotificationProps) {
  return (
    <div className="bobby-error-notification error">
      <strong className="notification-title">{props.title}</strong>
      <p className="notification-message">{props.message}</p>
    </div>
  );
}
