export interface taskDescriptionInterface {
  color?: string | null;
  label?: string | null;
}

export namespace setTaskDescription {
  export function setTaskDescription(data: taskDescriptionInterface): void;
}
